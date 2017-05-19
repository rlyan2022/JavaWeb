<%@ page language="java" pageEncoding="UTF-8" import="com.sanqing.course.model.*, com.sanqing.course.util.*,java.util.*" %>
<%
	String id = request.getParameter("id");
	
	Team team = Functions.getTeamById(id);
	
	List<CourseSchedule> courseSchedules = Functions.getCourseByTeam(team);
	
	System.out.println(courseSchedules.size());
	
	StringBuffer buf = new StringBuffer();
	buf.append("document.form2.courseId.length = " + (courseSchedules.size()+1) + ";\n");
	buf.append("document.form2.courseId.options[0].value = '';\n");
	buf.append("document.form2.courseId.options[0].text = '--请选择--';\n");
	buf.append("document.form2.courseId.selectedIndex = 0;\n");
	
	for(int i=0; i<courseSchedules.size(); i++) {
		CourseSchedule cs = (CourseSchedule)courseSchedules.get(i);

		buf.append("document.form2.courseId.options[" + (i+1) + "].value = '" + cs.getCourse().getId() + "';\n");
		buf.append("document.form2.courseId.options[" + (i+1) + "].text = '" + cs.getCourse().getName() + "';\n");
	}
	
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-store"); //HTTP1.1
	response.setHeader("Pragma", "no-cache"); //HTTP1.0
	response.setDateHeader("Expires", 0); //prevents catching at proxy server
	
	response.getWriter().write(buf.toString());
%>
