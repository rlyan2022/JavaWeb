package com.sanqing.struts.action;

import com.sanqing.po.CstCustomer;
import com.sanqing.po.CstService;
import com.sanqing.service.CstServiceService;
import com.sanqing.service.CustomerService;
import com.sanqing.struts.form.CstServiceForm;
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
import java.util.List;
import java.util.Map;

public class CstServiceAction extends DispatchAction {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
    private CustomerService custService = null;
    private CstServiceService cstServiceService = null;

    public CstServiceService getCstServiceService() {
        return cstServiceService;
    }

    public void setCstServiceService(CstServiceService cstServiceService) {
        this.cstServiceService = cstServiceService;
    }

    public CustomerService getCustService() {
        return custService;
    }

    public void setCustService(CustomerService custService) {
        this.custService = custService;
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

    // 查询客户服务信息
    public ActionForward doList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("查询客户服务信息");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 从页面接收参数
        LazyDynaBean ldb = new LazyDynaBean();
        BeanUtils.populate(ldb, request.getParameterMap());
        Map paramMap = ldb.getMap();
        PageResult pgr = cstServiceService.findAll(paramMap);
        System.out.println("总共有" + pgr.getRowCount() + "条客户服务信息");
        // 转换成JSON数据
        String jsonString = JSONSerializer.toJSON(pgr).toString();
        System.out.println(jsonString);
        out.print(jsonString);
        return null;
    }

    // 保存
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("创建客户服务信息");
        PrintWriter out = response.getWriter();
        // 获得客房名称，然后再查询出对应的编号
        String svrCustName = request.getParameter("svrCustName");
        System.out.println("名称:" + svrCustName);
        List<CstCustomer> customer = custService.findByCustName(svrCustName);
        String custName = customer.get(0).getCustNo();
        System.out.println("编号：" + custName);
        CstService cstService = new CstService();
        CstServiceForm cstServiceForm = (CstServiceForm) form;
        CstCustomer cstCustomer = new CstCustomer();
        cstCustomer.setCustNo(customer.get(0).getCustNo());
        cstService.setCstCustomer(cstCustomer);
        cstService.setSvrType(cstServiceForm.getSvrType());
        cstService.setSvrStatus(cstServiceForm.getSvrStatus());
        cstService.setSvrTitle(cstServiceForm.getSvrTitle());
        cstService.setSvrCreateBy(cstServiceForm.getSvrCreateBy());
        cstService.setSvrCreateDate(sf.format(new Date()));
        cstService.setSvrRequest(cstServiceForm.getSvrRequest());
        try {
            System.out.println("创建客户服务");
            cstServiceService.add(cstService);
            out.print("{success:true,msg:'创建成功'}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'创建失败'}");
        }
        return null;
    }

    // 服务分配
    public ActionForward doAllot(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("服务分配");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        CstServiceForm cstServiceForm = (CstServiceForm) form;
        // 根据服务编号查询出所有服务信息
        CstService cstService = cstServiceService.findById(cstServiceForm
                .getSvrId());
        // 获得分配人
        System.out.println("分配人给:" + cstServiceForm.getSvrDueTo());
        cstService.setSvrDueTo(cstServiceForm.getSvrDueTo());
        cstService.setSvrDueDate(sf.format(new Date()));
        cstService.setSvrStatus("已分配");
        try {
            cstServiceService.update(cstService);
            out.print("{success:true,msg:'分配成功'}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,分配失败'}");
        }
        return null;
    }

    // 服务处理
    public ActionForward doDispose(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("服务处理");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CstServiceForm cstServiceForm = (CstServiceForm) form;
        // 根据服务编号查询出CstService对象
        CstService cstService = cstServiceService.findById(cstServiceForm
                .getSvrId());
        cstService.setSvrStatus("已处理");
        cstService.setSvrDeal(cstServiceForm.getSvrDeal());
        cstService.setSvrDealBy(cstServiceForm.getSvrDealBy());
        cstService.setSvrDealDate(cstServiceForm.getSvrDealDate());
        try {
            cstServiceService.update(cstService);
            out.print("{success:true,msg:'处理成功'}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,处理失败'}");
        }
        return null;
    }

    // 服务反馈
    public ActionForward doFeedback(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("服务反馈");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CstServiceForm cstServiceForm = (CstServiceForm) form;
        // 根据服务编号查询出CstService对象
        System.out.println("编号为：" + cstServiceForm.getSvrId());
        CstService cstService = cstServiceService.findById(cstServiceForm
                .getSvrId());
        cstService.setSvrStatus("已归档");
        cstService.setSvrResult(cstServiceForm.getSvrResult());

        cstService.setSvrSatisfy(cstServiceForm.getSvrSatisfy());
        try {
            cstServiceService.update(cstService);
            out.print("{success:true,msg:'反馈成功'}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,反馈失败'}");
        }
        return null;
    }

    // 删除
    public ActionForward doDel(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("编号：" + request.getParameter("svrId"));
        try {
            CstService cstService = cstServiceService.findBySvrId(Long
                    .parseLong(request.getParameter("svrId")));
            cstServiceService.del(cstService);
            out.print("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("系统异常,删除失败");
        }
        return null;
    }
}