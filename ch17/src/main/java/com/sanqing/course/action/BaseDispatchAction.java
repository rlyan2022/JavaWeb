/**
 *
 */
package com.sanqing.course.action;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;



/**
 * @author Administrator
 *
 */
public class BaseDispatchAction extends DispatchAction {

	static {
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		ConvertUtils.register(new BigIntegerConverter(null), BigInteger.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new ByteConverter(null), Byte.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new SqlDateConverter(null), Date.class);
		ConvertUtils.register(new SqlTimeConverter(null), Time.class);
		ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class);
	}

	/**
	 * 保存单条信息到Message的简化函数.
	 */
	protected void saveMessage(HttpServletRequest request, String key,
							   String... values) {
		ActionMessages messages = new ActionMessages();
		messages.add( ActionMessages.GLOBAL_MESSAGE,
				new ActionMessage(key, values) );
		saveMessages(request.getSession(), messages);
	}

	/**
	 * 保存单条信息到Message的简化函数.
	 */
	protected void saveError(HttpServletRequest request, String key,
							 String... values) {
		ActionMessages errors = new ActionMessages();
		errors.add( ActionMessages.GLOBAL_MESSAGE,
				new ActionMessage(key, values) );
		saveErrors(request.getSession(), errors);
	}

	/**
	 * 获取用户请求页数
	 * @param request
	 * @return
	 */
	protected int getPageNo(HttpServletRequest request) {

		String strPageNo = request.getParameter("pageNo");

		int pageNo = 1;
		if(strPageNo == null || strPageNo.equals("")) {
			pageNo = 1;
		} else {
			try {
				pageNo = Integer.parseInt(strPageNo.trim());
				if(pageNo <= 0) pageNo = 1;
			} catch (NumberFormatException e) {
				pageNo = 1;
			}
		}

		return pageNo;
	}

}
