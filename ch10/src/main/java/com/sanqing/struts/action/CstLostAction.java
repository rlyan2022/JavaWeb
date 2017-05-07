package com.sanqing.struts.action;

import com.sanqing.po.CstCustomer;
import com.sanqing.po.CstLost;
import com.sanqing.service.CstLostService;
import com.sanqing.service.CustomerService;
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

/**
 * MyEclipse Struts Creation date: 08-30-2008
 * <p>
 * XDoclet definition:
 *
 * @struts.action path="/cstLost" name="cstLostForm" parameter="actionType"
 * scope="request" validate="true"
 */
public class CstLostAction extends DispatchAction {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
    private CstLostService cstLostService;        //客户流失业务逻辑层
    private CustomerService custService;        //客户业务逻辑层


    public void setCustService(CustomerService custService) {
        this.custService = custService;            //注入客户业务逻辑层
    }


    public void setCstLostService(CstLostService cstLostService) {
        this.cstLostService = cstLostService;    //注入客户流失业务逻辑层
    }

    // 如果没有传递actionType，默认执行这个方法
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 默认去入口
        PrintWriter out = response.getWriter();
        out.print("允许访问");
        return null;
    }

    public ActionForward doList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {                                //查询所有的流失客户
        request.setCharacterEncoding("UTF-8");                //设置请求编码格式
        response.setContentType("text/html;charset=UTF-8");    //设置响应文本格式
        PrintWriter out = response.getWriter();                //获得响应输出流
        LazyDynaBean ldb = new LazyDynaBean();
        BeanUtils.populate(ldb, request.getParameterMap());//获得请求参数
        Map paramMap = ldb.getMap();                        //获得Map对象请求参数
        PageResult pgr = cstLostService.findAll(paramMap);    //查询所有流失客户
        String pgrStr = JSONSerializer.toJSON(pgr).toString();//转换成JSON格式
        out.print(pgrStr);                                    //输出响应文本
        return null;
    }

    public ActionForward doDefLostorConfirmLost(ActionMapping mapping,
                                                ActionForm form, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");                //设置请求编码格式
        response.setContentType("text/html;charset=UTF-8");//设置响应文本格式
        PrintWriter out = response.getWriter();                //获得响应输出流
        String lstId = request.getParameter("lstId");        //获得流失客户编号
        try {
            CstLost cstLost = cstLostService.
                    findByLstId(Long.parseLong(lstId));//查找该流失客户
            if ("defLost".equals(request.getParameter("type"))) {//暂缓流失
                cstLost.setLstStatus("暂缓流失");
                cstLost.setLstDelay(cstLost.getLstDelay()
                        + request.getParameter("lstAddDelay"));    //暂缓日期
            } else {
                // 在流失表中把客户的状态改为“已经流失”,并加上流失原因和流失时间
                cstLost.setLstStatus("已经流失");                    //确认流失
                cstLost.setLstLostDate(new Date());                //流失时间
                cstLost.setLstReason(request.getParameter("lstReason"));
                // 在客户信息表中把客户的状态改为“2”即已流失
                CstCustomer customer = custService.findByCustNo(cstLost
                        .getCstCustomer().getCustNo());            //查找客户信息
                customer.setCustStatus("2");                    //设置客户为流失状态
                custService.update(customer);                    //更新客户信息
            }
            cstLostService.update(cstLost);                        //更新流失客户信息
            out.print("{success:true,msg:'操作成功'}");            //输出成功信息
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,操作失败'}");    //输出错误信息
        }
        return null;
    }

}