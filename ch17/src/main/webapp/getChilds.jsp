<%@ page language="java" pageEncoding="UTF-8" import="com.sanqing.course.model.*, com.sanqing.course.util.*,java.util.*" %>
<%
	String id = request.getParameter("id");
	
	Course course = Functions.getCourseById(id);
	
	Set<Teacher> teachers = course.getTeachers();
	
	System.out.println(teachers.size());
	
	StringBuffer buf = new StringBuffer();
	buf.append("document.courseScheduleForm.teacherId.length = " + (teachers.size()+1) + ";\n");
	buf.append("document.courseScheduleForm.teacherId.options[0].value = '';\n");
	buf.append("document.courseScheduleForm.teacherId.options[0].text = '--请选择--';\n");
	buf.append("document.courseScheduleForm.teacherId.selectedIndex = 0;\n");
	
	int i=0;
	for(Iterator iter = teachers.iterator(); iter.hasNext(); i++) {
		Teacher t = (Teacher)iter.next();

		buf.append("document.courseScheduleForm.teacherId.options[" + (i+1) + "].value = '" + t.getId() + "';\n");
		buf.append("document.courseScheduleForm.teacherId.options[" + (i+1) + "].text = '" + t.getName() + "';\n");
	}
	
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-store"); //HTTP1.1
	response.setHeader("Pragma", "no-cache"); //HTTP1.0
	response.setDateHeader("Expires", 0); //prevents catching at proxy server
	
	response.getWriter().write(buf.toString());
%>
