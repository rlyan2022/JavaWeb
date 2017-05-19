<%@ page language="java" pageEncoding="UTF-8" import="com.sanqing.course.model.*, com.sanqing.course.util.*,java.util.*" %>
<%
	String id = request.getParameter("id");
	
	Teacher teacher = (Teacher)session.getAttribute("teacher");
	Team team = Functions.getTeamById(id);
	
	List<Course> courses = Functions.getCourseByTeamAndTeacher(team, teacher);
	
	System.out.println(courses.size());
	
	StringBuffer buf = new StringBuffer();
	buf.append("document.courseScheduleForm.courseId.length = " + (courses.size()+1) + ";\n");
	buf.append("document.courseScheduleForm.courseId.options[0].value = '';\n");
	buf.append("document.courseScheduleForm.courseId.options[0].text = '--请选择--';\n");
	buf.append("document.courseScheduleForm.courseId.selectedIndex = 0;\n");
	
	for(int i=0; i<courses.size(); i++) {
		Course c = (Course)courses.get(i);

		buf.append("document.courseScheduleForm.courseId.options[" + (i+1) + "].value = '" + c.getId() + "';\n");
		buf.append("document.courseScheduleForm.courseId.options[" + (i+1) + "].text = '" + c.getName() + "';\n");
	}
	
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-store"); //HTTP1.1
	response.setHeader("Pragma", "no-cache"); //HTTP1.0
	response.setDateHeader("Expires", 0); //prevents catching at proxy server
	
	response.getWriter().write(buf.toString());
%>
