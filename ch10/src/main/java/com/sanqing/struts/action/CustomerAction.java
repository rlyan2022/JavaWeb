package com.sanqing.struts.action;

import com.sanqing.po.CstActivity;
import com.sanqing.po.CstCustomer;
import com.sanqing.po.CstLinkman;
import com.sanqing.po.CstManager;
import com.sanqing.service.*;
import com.sanqing.struts.form.CustomerForm;
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

public class CustomerAction extends DispatchAction {
    private CustomerService custService;        //客户信息业务逻辑层
    private CstLinkmanService cstLinkmanService;//联系人业务逻辑层
    private CstActivityService cstActivityService;//交往记录业务逻辑层
    private OrdersService ordersService;    //客户订单业务逻辑层
    private CstManagerService cstManService = null;

    public CustomerService getCustService() {
        return custService;
    }

    public void setCustService(CustomerService custService) {
        this.custService = custService;        //注入客户信息业务逻辑层
    }

    public CstLinkmanService getCstLinkmanService() {
        return cstLinkmanService;
    }

    public void setCstLinkmanService(CstLinkmanService cstLinkmanService) {
        this.cstLinkmanService = cstLinkmanService;//注入联系人业务逻辑层
    }

    public CstActivityService getCstActivityService() {
        return cstActivityService;
    }

    public void setCstActivityService(CstActivityService cstActivityService) {
        this.cstActivityService = cstActivityService;//注入交往记录业务逻辑层
    }

    public OrdersService getOrdersService() {
        return ordersService;
    }

    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;            //注入客户订单业务逻辑层
    }

    public CstManagerService getCstManService() {
        return cstManService;
    }

    public void setCstManService(CstManagerService cstManService) {
        this.cstManService = cstManService;
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

	/*
     * 管理客户信息 @method doList,doSaveorUpdate
	 */

    public ActionForward doList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {//查询所有客户信息
        response.setContentType("text/html;charset=UTF-8");//设置响应文本格式
        PrintWriter out = response.getWriter();                //获得响应输出流
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());    //获得请求参数
        Map paramMap = ll.getMap();                            //获得参数封装的MAP
        PageResult pgr = custService.findAll(paramMap);        //查询所有客户信息
        String pgrStr = JSONSerializer.toJSON(pgr).toString();// 转换成JSON格式
        out.print(pgrStr);                                    //输出查询结果
        out.close();                                        //关闭响应输出流
        return null;
    }

    // 查询客户信息
    public ActionForward doFindAllManager(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 获得参数
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());
        Map paramMap = ll.getMap();
        // 查询
        PageResult pgr = cstManService.findAllCstManager(paramMap);
        // 转换成JSON格式
        String pgrStr = JSONSerializer.toJSON(pgr).toString();
        System.out.println(pgrStr);
        out.print(pgrStr);
        out.close();
        return null;
    }

    // 新增或修改客户信息
    public ActionForward doSaveorUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CstCustomer customer = new CstCustomer();
        CustomerForm custForm = (CustomerForm) form;
        // 获得页面提交过来的参数
        customer.setCustNo(custForm.getCustNo());
        System.out.println("客户编号为：" + custForm.getCustNo());
        customer.setCustName(custForm.getCustName());
        customer.setCustRegion(custForm.getCustRegion());
        String custManagerName = custForm.getCustManagerName();
        // 根据经理名称找编号
        Long manId = cstManService.findManId(custManagerName);
        CstManager cstManager = new CstManager();
        System.out.println("客户经理编号为：" + request.getParameter("custManId"));
        cstManager.setManId(manId);
        customer.setCstManager(cstManager);
        // 判断客户等级
        String custLevel = custForm.getCustLevel();
        Integer level;
        if ("普通客户".equals(custLevel)) {
            level = 1;
        } else if ("重点开发客户".equals(custLevel)) {
            level = 2;
        } else if ("大客户".equals(custLevel)) {
            level = 3;
        } else if ("合作伙伴".equals(custLevel)) {
            level = 4;
        } else {
            level = 5;
        }
        customer.setCustLevel(level);
        // 判断客户满意度
        String custSatisfy = custForm.getCustSatisfy();
        Integer satisfy;
        if ("☆".equals(custSatisfy)) {
            satisfy = 1;
        } else if ("☆☆".equals(custSatisfy)) {
            satisfy = 2;
        } else if ("☆☆☆".equals(custSatisfy)) {
            satisfy = 3;
        } else if ("☆☆☆☆".equals(custSatisfy)) {
            satisfy = 4;
        } else {
            satisfy = 5;
        }
        customer.setCustSatisfy(satisfy);
        // 判断客户满意度
        String custCredit = custForm.getCustCredit();
        Integer credit;
        if ("☆".equals(custCredit)) {
            credit = 1;
        } else if ("☆☆".equals(custCredit)) {
            credit = 2;
        } else if ("☆☆☆".equals(custCredit)) {
            credit = 3;
        } else if ("☆☆☆☆".equals(custCredit)) {
            credit = 4;
        } else {
            credit = 5;
        }
        customer.setCustCredit(credit);
        customer.setCustAddr(custForm.getCustAddr());
        customer.setCustZip(custForm.getCustZip());
        customer.setCustTel(custForm.getCustTel());
        customer.setCustFax(custForm.getCustFax());
        customer.setCustWebsite(custForm.getCustWebsite());
        customer.setCustLicenceNo(custForm.getCustLicenceNo());
        customer.setCustChieftain(custForm.getCustChieftain());
        customer.setCustBankroll(custForm.getCustBankroll());
        customer.setCustTurnover(custForm.getCustTurnover());
        customer.setCustBank(custForm.getCustBank());
        customer.setCustBankAccount(custForm.getCustBankAccount());
        customer.setCustLocalTaxNo(custForm.getCustLocalTaxNo());
        customer.setCustNationalTaxNo(custForm.getCustNationalTaxNo());
        customer.setCustStatus("1");
        try {
            // 若客户编号不存在，则新增一条客户信息
            if (custService.findById(custForm.getCustNo()) == false) {
                System.out.println("新增客户信息");
                custService.add(customer);
                out.print("{success:true,msg:'增加成功'}");
            } else {// 否则为修改
                System.out.println("修改客户信息");
                custService.update(customer);
                out.print("{success:true,msg:'修改成功'}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,data:'系统异常,保存失败'}");
        }
        return null;
    }

    // 删除客户信息
    public ActionForward doDel(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("删除客户信息");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 先根据客户编号来判断该客户的信息是否与数据库中的信息相匹配，如果匹配则返回一个CstCustomer对象
        try {
            CstCustomer customer = custService.findByCustNo(request
                    .getParameter("custNo"));
            custService.del(customer);
            out.print("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("删除失败");
        }

        return null;
    }

	/*
	 * 客户联系人信息管理 @method doFindLinkman,doSaveorUpdateLinkman,doDelLinkman
	 */

    public ActionForward doFindLinkman(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {                //根据客户编号查询该客户的所有联系人
        request.setCharacterEncoding("UTF-8");                //设置请求编码格式
        response.setContentType("text/html;charset=UTF-8");//设置响应文本格式
        PrintWriter out = response.getWriter();                //获得响应输出流
        LazyDynaBean ldb = new LazyDynaBean();
        BeanUtils.populate(ldb, request.getParameterMap());//获得请求参数
        Map paramMap = ldb.getMap();                        //获得参数封装的MAP
        PageResult pgr = cstLinkmanService.findLinkman(paramMap);//查询所有联系人
        String pgrStr = JSONSerializer.toJSON(pgr).toString();//转换成JSON格式
        out.print(pgrStr);                                    //输出查询结果
        return null;
    }

    // 新增和修改联系人信息
    public ActionForward doSaveorUpdateLinkman(ActionMapping mapping,
                                               ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        CstLinkman cstLinkman = new CstLinkman();
        // 获得客户编号
        String lkmCustNo = request.getParameter("lkmCustNo");
        CstCustomer cstCustomer = new CstCustomer();
        cstCustomer.setCustNo(lkmCustNo);
        cstLinkman.setCstCustomer(cstCustomer);
        String lkmId = request.getParameter("lkmId");
        cstLinkman.setLkmName(request.getParameter("lkmName"));
        cstLinkman.setLkmSex(request.getParameter("lkmSex"));
        cstLinkman.setLkmPostion(request.getParameter("lkmPostion"));
        cstLinkman.setLkmTel(request.getParameter("lkmTel"));
        cstLinkman.setLkmMobile(request.getParameter("lkmMobile"));
        cstLinkman.setLkmMemo(request.getParameter("lkmMemo"));
        try {
            // 根据客户联系人的编号来判断是增还是改，如果联系人编号为空则新增
            if (lkmId == "" || lkmId == null) {
                System.out.println("新增客户联系人信息");
                cstLinkmanService.addLinkman(cstLinkman);
                out.print("{success:true,msg:'新增成功'}");
            } else {
                System.out.println("修改客户联系人信息");
                cstLinkman.setLkmId(Long.parseLong(lkmId));
                cstLinkmanService.updateLinkman(cstLinkman);
                out.print("{success:true,msg:'修改成功'}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,保存失败'}");
        }
        return null;
    }

    // 删除联系人信息
    public ActionForward doDelLinkman(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("删除客户联系人信息");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            CstLinkman cstLinkman = cstLinkmanService.findById(Long
                    .parseLong(request.getParameter("lkmId")));
            cstLinkmanService.delLinkman(cstLinkman);
            out.print("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("删除失败");
        }
        return null;
    }

	/*
	 * 客户交往活动管理 @method
	 * dofindCstActivity,doSaveorUpdateCstActivity,doDelCstActivity
	 */

    public ActionForward dofindCstActivity(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {                    //查询所有客户交往记录
        response.setContentType("text/html;charset=UTF-8");//设置响应文本格式
        PrintWriter out = response.getWriter();            //获得响应输出流
        LazyDynaBean ll = new LazyDynaBean();            //获得请求参数
        BeanUtils.populate(ll, request.getParameterMap());//获得参数封装的MAP
        Map paramMap = ll.getMap();
        PageResult pgr = cstActivityService.findAll(paramMap);//查询所有交往记录
        String pgrStr = JSONSerializer.toJSON(pgr).toString();// 转换成JSON格式
        out.print(pgrStr);                                //转换成JSON格式
        out.close();                                    //输出查询结果
        return null;
    }

    // 新增或修改客户交往记录
    public ActionForward doSaveorUpdateCstActivity(ActionMapping mapping,
                                                   ActionForm form, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CstActivity cstActivity = new CstActivity();
        // 参数
        // CstCustomer对象
        System.out.println("客户编号为：" + request.getParameter("atvCustNo"));
        CstCustomer cstCustomer = new CstCustomer();
        cstCustomer.setCustNo(request.getParameter("atvCustNo"));
        String atvId = request.getParameter("atvId");

        cstActivity.setCstCustomer(cstCustomer);
        cstActivity.setAtvDate(request.getParameter("atvDate"));
        cstActivity.setAtvPlace(request.getParameter("atvPlace"));
        cstActivity.setAtvTitle(request.getParameter("atvTitle"));
        cstActivity.setAtvDesc(request.getParameter("atvDesc"));
        cstActivity.setAtvRemark(request.getParameter("atvRemark"));
        try {
            if (atvId == "" || atvId == null) {
                System.out.println("新增客户交往记录");
                cstActivityService.addCstActivity(cstActivity);
                out.print("{success:true,msg:'新建成功'}");
            } else {
                System.out.println("修改客户交往记录");
                cstActivity.setAtvId(Long.parseLong(atvId));
                cstActivityService.updateCstActivity(cstActivity);
                out.print("{success:true,msg:'修改成功'}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:true,msg:'系统异常,修改失败'}");
        }
        return null;
    }

    // 删除客户交往记录
    public ActionForward doDelCstActivity(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 获得要删除的编号
        String atvId = request.getParameter("atvId");
        CstActivity cstActivity = cstActivityService.findById(new Long(atvId));
        try {
            cstActivityService.delCstActivity(cstActivity);
            out.print("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("系统异常,删除失败");
        }
        return null;
    }

	/*
	 * 客户历史订单管理 @method doFindOrders,doFindDetailList
	 */

    public ActionForward doFindOrders(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {                                // 查询历史订单
        request.setCharacterEncoding("UTF-8");                //设置请求编码格式
        response.setContentType("text/html;charset=UTF-8");    //设置响应文本格式
        PrintWriter out = response.getWriter();                //获得响应输出流
        LazyDynaBean ldb = new LazyDynaBean();
        BeanUtils.populate(ldb, request.getParameterMap());    //获得请求参数
        Map paramMap = ldb.getMap();                        //获得Map对象请求参数
        PageResult pgr = ordersService.findAllOrders(paramMap);//查询所有订单
        String pgrStr = JSONSerializer.toJSON(pgr).toString();//转换成JSON格式
        out.print(pgrStr);                                    //输出响应文本
        return null;
    }


    public ActionForward doFindDetailList(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {// 查询订单明细
        request.setCharacterEncoding("UTF-8");                //设置请求编码格式
        response.setContentType("text/html;charset=UTF-8");//设置响应文本格式
        PrintWriter out = response.getWriter();                //获得响应输出流
        LazyDynaBean ldb = new LazyDynaBean();
        BeanUtils.populate(ldb, request.getParameterMap());//获得请求参数
        Map paramMap = ldb.getMap();                        //获得Map对象请求参数
        PageResult pgr = ordersService.findOrdersLine(paramMap);//查询订单详细情况
        String pgrStr = JSONSerializer.toJSON(pgr).toString();//转换成JSON格式
        out.print(pgrStr);                                    //输出响应文本
        return null;
    }

}