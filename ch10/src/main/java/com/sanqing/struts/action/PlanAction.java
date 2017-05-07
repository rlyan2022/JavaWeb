package com.sanqing.struts.action;

import com.sanqing.po.SalChance;
import com.sanqing.po.SalPlan;
import com.sanqing.service.PlanService;
import com.sanqing.struts.form.PlanForm;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PlanAction extends DispatchAction {
    private PlanService planService = null;

    public PlanService getPlanService() {
        return planService;
    }

    public void setPlanService(PlanService planService) {
        this.planService = planService;
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

    // 查询存在的销售机会记录。。。。。
    public ActionForward doList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // PlanForm planForm = (PlanForm) form;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("查询已存在的销售机会。。。。");
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());
        Map paramMap = ll.getMap();
        PageResult pgr = planService.findAll(paramMap);
        System.out.println("总共有" + pgr.getRowCount() + "条销售机会记录");
        String pgrstr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrstr);
        out.print(pgrstr);
        out.close();
        return null;
    }

    // 查询销售机会对应的计划项。。。。。
    public ActionForward doFindTodo(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // PlanForm planForm = (PlanForm) form;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("查询计划项。。。。");
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());
        Map paramMap = ll.getMap();
        PageResult pgr = planService.findTodo(paramMap);
        System.out.println("总共有" + pgr.getRowCount() + "条计划项");
        String pgrstr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrstr);
        out.print(pgrstr);
        out.close();
        return null;
    }

    // 保存或修改计划项。。。。
    public ActionForward doSaveorUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getParameter("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SalPlan salPlan = new SalPlan();
        PlanForm planForm = (PlanForm) form;
        salPlan.setPlaId(planForm.getPlaId());
        System.out.println("销售机会编号：" + request.getParameter("chcId"));
        System.out.println("计划项编号：" + request.getParameter("plaId"));
        SalChance salChance = new SalChance();
        salChance.setChcId(Long.parseLong(request.getParameter("chcId")));
        salPlan.setSalChance(salChance);

        salPlan.setPlaTodo(planForm.getPlaTodo());
        System.out.println("计划项：" + planForm.getPlaTodo());
        salPlan.setPlaResult(planForm.getPlaResult());
        try {
            if (planService.findById(planForm.getPlaId()) == false) {
                System.out.println("新增计划项");
                SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
                salPlan.setPlaDate(sf.format(new Date()));
                planService.addPlan(salPlan);
                out.print("{success:true,msg:'添加成功'}");
            } else if (planService.findById(planForm.getPlaId()) == true) {
                System.out.println("修改计划项");
                salPlan.setPlaDate(planForm.getPlaDate());
                planService.updatePlan(salPlan);
                out.print("{success:true,msg:'修改成功'}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,保存失败'}");
        }
        return null;
    }

    // 保存执行结果。。。。
    public ActionForward doSaveResult(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getParameter("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PlanForm planForm = (PlanForm) form;
        String plaId = request.getParameter("plaId");
        System.out.println("计划项编号：" + plaId);
        SalPlan salPlan = planService.findByPlaId(Long.parseLong(plaId));
        salPlan.setPlaResult(request.getParameter("plaResult"));
        try {
            System.out.println("执行计划项");
            planService.updatePlan(salPlan);
            out.print("{success:true,msg:'执行成功'}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,执行失败'}");
        }
        return null;
    }

    // 开发成功或开发失败。。。。
    public ActionForward doSuccessorFailure(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        request.getParameter("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SalChance salChance = planService.findByChcId(Long.parseLong(request
                .getParameter("chcId")));
        try {
            if ("success".equals(request.getParameter("type"))) {
                salChance.setChcStatus("3");
                out.print("开发成功!");
            } else if ("failure".equals(request.getParameter("type"))) {
                salChance.setChcStatus("4");
                out.print("开发失败!");
            }
            planService.updateChance(salChance);
        } catch (Exception e) {
            e.printStackTrace();
            out.print("系统异常,操作失败");
        }
        return null;
    }

    // 删除。。。。
    public ActionForward doDel(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getParameter("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SalPlan salPlan = planService.findByPlaId(Long.parseLong(request
                .getParameter("plaId")));
        try {
            planService.delPlan(salPlan);
            out.print("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("系统异常,删除失败");
        }
        return null;
    }
}