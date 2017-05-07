package com.sanqing.struts.action;

import com.sanqing.po.SysRole;
import com.sanqing.po.SysUser;
import com.sanqing.service.UserinfoService;
import com.sanqing.struts.form.UserinfoForm;
import com.sanqing.util.PageResult;
import net.sf.json.JSONSerializer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class UserinfoAction extends DispatchAction {
    private UserinfoService userinfoService = null;

    public UserinfoService getUserinfoService() {
        return userinfoService;
    }

    public void setUserinfoService(UserinfoService userinfoService) {
        this.userinfoService = userinfoService;
    }

    // 如果没有传递actionType，默认执行这个方法
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("默认方法...");
        // 默认去入口
        PrintWriter out = response.getWriter();
        out.print("允许访问");
        return null;
    }

    public ActionForward doList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("查询用户信息");
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());
        Map paramMap = ll.getMap();
        PageResult pgr = userinfoService.findAll(paramMap);
        System.out.println("总共有" + pgr.getRowCount() + "条用户信息");
        String pgrstr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrstr);
        out.print(pgrstr);
        out.close();
        return null;
    }

    // 查询出所有角色名称
    public ActionForward doFindAllRole(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PageResult pgr = userinfoService.findAllRoleName();
        System.out.println("总共有" + pgr.getRowCount() + "个角色");
        String pgrstr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrstr);
        out.print(pgrstr);
        out.close();
        return null;
    }

    // 查询出所有权限名称
//	public ActionForward doFindAllRight(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		LazyDynaBean ll = new LazyDynaBean();
//		BeanUtils.populate(ll, request.getParameterMap());
//		Map paramMap = ll.getMap();
//		PageResult pgr = userinfoService.findAllRightName(paramMap);
//		String pgrstr = JSONSerializer.toJSON(pgr).toString();
//		System.out.println(pgrstr);
//		out.print(pgrstr);
//		out.close();
//		return null;
//	}

    // 添加用戶信息
    public ActionForward doSaveorUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("添加或修改用戶信息");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SysUser sysUser = new SysUser();
        UserinfoForm userinfoForm = (UserinfoForm) form;
        // 获得页面提交过来的参数
        sysUser.setUsrId(userinfoForm.getUsrId());
        sysUser.setUsrName(userinfoForm.getUsrName());
        sysUser.setUsrPassword(userinfoForm.getUsrPassword());
        Long roleId = Long.parseLong(request.getParameter("roleName"));
        System.out.println("角色编号：" + roleId);
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(roleId);
        sysUser.setSysRole(sysRole);
        try {
            // 若该营销机会编号不存在，则新增一条营销机会信息
            if (userinfoService.findById(userinfoForm.getUsrId()) == false) {
                System.out.println("添加用户信息");
                userinfoService.add(sysUser);
                out.print("{success:true,msg:'增加成功'}");
            } else {// 否则为修改
                System.out.println("修改用户信息");
                userinfoService.update(sysUser);
                out.print("{success:true,msg:'修改成功'}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,保存失败'}");
        }
        return null;
    }

    // 删除用户信息
    public ActionForward doDel(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            SysUser sysUser = userinfoService.findByUsrId(Long
                    .parseLong(request.getParameter("usrId")));
            userinfoService.del(sysUser);
            out.print("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("系统异常,删除失败");
        }

        return null;
    }

}