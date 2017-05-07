package com.sanqing.struts.action;

import com.sanqing.po.BasDict;
import com.sanqing.service.BasDictService;
import com.sanqing.struts.form.BasDictForm;
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

public class BasDictAction extends DispatchAction {
    private BasDictService dictService;                //数据字典业务逻辑层

    public void setDictService(BasDictService dictService) {
        this.dictService = dictService;                //注入数据字典业务逻辑层
    }


    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws IOException {// 如果没有传递actionType，默认执行这个方法
        PrintWriter out = response.getWriter();
        out.print("允许访问");
        return null;
    }


    public ActionForward doList(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {// 查询所有数据字典信息
        response.setContentType("text/html;charset=UTF-8");//设置响应文本格式
        PrintWriter out = response.getWriter();            //获得响应输出流
        LazyDynaBean ll = new LazyDynaBean();
        BeanUtils.populate(ll, request.getParameterMap());//获得请求参数
        Map paramMap = ll.getMap();                        //获得参数封装的MAP
        PageResult pgr = dictService.findAll(paramMap);    //查询所有数据字典信息
        String pgrstr = JSONSerializer.toJSON(pgr).toString();//转换成JSON格式
        out.print(pgrstr);                                //输出查询结果
        out.close();                                    //关闭响应输出流
        return null;
    }


    public ActionForward doSaveandUpdate(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {// 新建或修改数据字典信息
        response.setContentType("text/html;charset=UTF-8");//设置响应文本格式
        PrintWriter out = response.getWriter();                //获得响应输出流
        BasDict basdict = new BasDict();                    //实例化数据字典信息对象
        BasDictForm basdform = (BasDictForm) form;
        basdict.setDictId(basdform.getDictId());            //设置字典编号
        basdict.setDictType(basdform.getDictType());        //设置字典类别
        basdict.setDictItem(basdform.getDictItem());        //设置字典条目
        basdict.setDictValue(basdform.getDictValue());        //设置字典数据
        basdict.setDictIsEditable(basdform.getDictIsEditable());//设置是否可以编辑
        try {
            //如果数据字典ID是空的话就是新建
            if (dictService.findById(basdform.getDictId()) == false) {
                dictService.add(basdict);                    //保存字典信息
                out.print("{success:true,msg:'增加成功'}");    //输出成功字符串
            } else {// 否则就是修改
                dictService.update(basdict);                //修改字典信息
                out.print("{success:true,msg:'修改成功'}");    //输出成功字符串
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,data:'系统异常,保存失败'}");    //输出失败字符串
        }
        return null;
    }

    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {// 删除数据字典信息
        response.setContentType("text/html;charset=UTF-8");//设置响应文本格式
        PrintWriter out = response.getWriter();                //获得响应输出流
        BasDict basdict = dictService.findBydictId(Long.
                parseLong(request.getParameter("dictId")));//查询字典信息
        try {
            dictService.delete(basdict);                    //删除字典信息
            out.print("删除成功");                            //输出成功字符串
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,data:'系统异常,保存失败'}");//输出失败字符串
        }
        return null;
    }

}