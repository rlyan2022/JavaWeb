package com.sanqing.struts.action;

import com.sanqing.po.SalChance;
import com.sanqing.service.ChanceService;
import com.sanqing.service.CstManagerService;
import com.sanqing.service.CustomerService;
import com.sanqing.struts.form.ChanceForm;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ChanceAction extends DispatchAction {

    private ChanceService chanceService = null;
    private CstManagerService cstManService = null;
    private CustomerService cstService = null;

    public CustomerService getCstService() {
        return cstService;
    }

    public void setCstService(CustomerService cstService) {
        this.cstService = cstService;
    }

    public CstManagerService getCstManService() {
        return cstManService;
    }

    public void setCstManService(CstManagerService cstManService) {
        this.cstManService = cstManService;
    }

    public ChanceService getChanceService() {
        return chanceService;
    }

    public void setChanceService(ChanceService chanceService) {
        this.chanceService = chanceService;
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

    // 查询销售机会信息
    public ActionForward doList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter out = response.getWriter();
        System.out.println("查询营销机会信息");
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());
        Map paramMap = ll.getMap();
        PageResult pgr = chanceService.findAll(paramMap);
        System.out.println("总共有" + pgr.getRowCount() + "条营销机会信息");
        String pgrstr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrstr);
        out.print(pgrstr);
        out.close();
        return null;
    }

    // 查询所有客户
    public ActionForward doFindAllCustomer(ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());
        Map paramMap = ll.getMap();
        PageResult pgr = cstService.findAll(paramMap);
        String pgrstr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrstr);
        out.print(pgrstr);
        out.close();
        return null;
    }

    // 查询所有客户经理的名称
    public ActionForward doFindAllCstManager(ActionMapping mapping,
                                             ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        System.out.println("查询所有客户经理的名称");
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());
        Map paramMap = ll.getMap();
        PageResult pgr = cstManService.findAllCstManager(paramMap);
        System.out.println("总共有" + pgr.getRowCount() + "客户经理");
        String pgrstr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrstr);
        out.print(pgrstr);
        out.close();
        return null;
    }

    // 增加或修改销售机会信息
    public ActionForward doSaveorUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter out = response.getWriter();
        SalChance chance = new SalChance();
        ChanceForm chanceForm = (ChanceForm) form;

        // 获得页面提交过来的参数
        chance.setChcId(chanceForm.getChcId());
        chance.setChcSource(chanceForm.getChcSource());
        chance.setChcCustName(chanceForm.getChcCustName());
        chance.setChcTitle(chanceForm.getChcTitle());
        chance.setChcRate(chanceForm.getChcRate());
        chance.setChcLinkman(chanceForm.getChcLinkman());
        chance.setChcTel(chanceForm.getChcTel());
        chance.setChcDesc(chanceForm.getChcDesc());
        chance.setChcCreateBy(chanceForm.getChcCreateBy());
        System.out.println("创建人为：" + request.getParameter("chcCreateBy"));
        // 对创建日期进行格式化
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        chance.setChcCreateDate(sf.format(new Date()));
        chance.setChcStatus("1");
        System.out.println("从页面传过来的参数： " + chanceForm.getChcId());
        try {
            // 若该营销机会编号不存在，则新增一条营销机会信息
            if (chanceService.findById(chanceForm.getChcId()) == false) {
                System.out.println("新增营销机会信息");
                chanceService.add(chance);
                out.print("{success:true,msg:'增加成功'}");
            } else {// 否则为修改
                System.out.println("修改营销机会信息");
                chanceService.update(chance);
                out.print("{success:true,msg:'修改成功'}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,保存失败'}");
        }
        return null;
    }

    // 指派销售机会
    public ActionForward doAssign(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("指派销售机会");
        PrintWriter out = response.getWriter();
        String chcId = request.getParameter("chcId");
        String chcDueTo = request.getParameter("chcDueTo");
        String chcDueDate = request.getParameter("chcDueDate");
        try {
            // 根据销售机会编号进行指派
            SalChance salChance = chanceService.findByChcId(Long
                    .parseLong(chcId));
            salChance.setChcDueTo(chcDueTo);
            // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            salChance.setChcDueDate(chcDueDate);
            salChance.setChcStatus("2");
            chanceService.update(salChance);
            out.print("{success:true,msg:'指派成功'}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,指派失败'}");
        }
        return null;
    }

    // 删除销售机会信息
    public ActionForward doDel(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        try {
            SalChance salChance = chanceService.findByChcId(Long
                    .parseLong(request.getParameter("chcId")));
            // 判断当前用户是否为销售机会的创建人，如果是则有权删除
            if (!userName.equals(salChance.getChcCreateBy())) {
                out.print("对不起,您无权删除");
            } else {
                chanceService.del(salChance);
                out.print("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("系统异常,删除失败");
        }

        return null;
    }

}