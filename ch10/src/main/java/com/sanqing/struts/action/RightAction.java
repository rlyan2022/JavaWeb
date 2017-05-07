package com.sanqing.struts.action;

import com.sanqing.po.SysRight;
import com.sanqing.service.RightService;
import com.sanqing.struts.form.RightForm;
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

public class RightAction extends DispatchAction {
    private RightService rightService = null;

    public RightService getRightService() {
        return rightService;
    }

    public void setRightService(RightService rightService) {
        this.rightService = rightService;
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

    // 查询。。。
    public ActionForward doList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("权限管理。。。。");
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());
        Map paramMap = ll.getMap();
        PageResult pgr = rightService.findAll(paramMap);
        String pgrstr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrstr);
        out.print(pgrstr);
        out.close();
        return null;
    }

    // 添加修改权限。。。
    public ActionForward doSaveorUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("添加权限。。。");
        RightForm rightForm = (RightForm) form;
        SysRight sysRight = new SysRight();
        sysRight.setRightText(rightForm.getRightText());
        System.out.println("权限:" + rightForm.getRightText());
        sysRight.setRightUrl(rightForm.getRightUrl());
        Long rightCode = rightForm.getRightCode();
        try {
            if (rightCode == null || rightCode == 0) {
                rightService.addRight(sysRight);
                out.print("{success:true,msg:'添加成功'}");
            } else {
                sysRight.setRightCode(rightCode);
                rightService.updateRight(sysRight);
                out.print("{success:false,msg:'修改成功'}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,添加失败'}");
        }
        return null;
    }

    // 删除权限。。。。
    public ActionForward doDel(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("删除权限。。。");
        RightForm rightForm = (RightForm) form;
        System.out.println(rightForm.getRightCode());
        Long rightCode = Long.parseLong(request.getParameter("rightCode"));
        try {
            if (rightService.isRoleRight(rightCode) == false) {
                SysRight sysright = rightService.findRightId(rightCode);
                rightService.delRight(sysright);
                out.print("删除成功");
            } else {
                out.print("此权限已被使用,不能删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("系统异常,删除失败");
        }
        out.close();
        return null;
    }
}