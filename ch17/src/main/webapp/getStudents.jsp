<%@ page language="java" pageEncoding="UTF-8" import="com.sanqing.course.model.*, com.sanqing.course.util.*,java.util.*" %>
<%
	String id = request.getParameter("id");
	StringBuffer buf = new StringBuffer();
	
	if(id.trim().length() > 0) {
	
		Team team = Functions.getTeamById(id);
		
		Set<Student> students = team.getStudents();
		List<CourseSchedule> courseSchedules = Functions.getCourseByTeam(team);
		
		System.out.println(students.size());
		System.out.println(courseSchedules.size());
		
		buf.append("document.markForm.studentId.length = " + (students.size()+1) + ";\n");
		buf.append("document.markForm.studentId.options[0].value = '';\n");
		buf.append("document.markForm.studentId.options[0].text = '--请选择--';\n");
		buf.append("document.markForm.studentId.selectedIndex = 0;\n");

		buf.append("document.markForm.courseId.length = " + (courseSchedules.size()+1) + ";\n");
		buf.append("document.markForm.courseId.options[0].value = '';\n");
		buf.append("document.markForm.courseId.options[0].text = '--请选择--';\n");
		buf.append("document.markForm.courseId.selectedIndex = 0;\n");

		int i=0;
		for(Iterator iter = students.iterator(); iter.hasNext(); i++) {
			Student s = (Student)iter.next();

			buf.append("document.markForm.studentId.options[" + (i+1) + "].value = '" + s.getId() + "';\n");
			buf.append("document.markForm.studentId.options[" + (i+1) + "].text = '" + s.getCode() + "';\n");
		}

		int j=0;
		for(Iterator iter = courseSchedules.iterator(); iter.hasNext(); j++) {
			CourseSchedule cs = (CourseSchedule)iter.next();

			buf.append("document.markForm.courseId.options[" + (j+1) + "].value = '" + cs.getCourse().getId() + "';\n");
			buf.append("document.markForm.courseId.options[" + (j+1) + "].text = '" + cs.getCourse().getName() + "';\n");
		}
	} else {
		List<Course> courses = Functions.getCourseList();

		buf.append("document.markForm.courseId.length = " + (courses.size()+1) + ";\n");
		buf.append("document.markForm.courseId.options[0].value = '';\n");
		buf.append("document.markForm.courseId.options[0].text = '--请选择--';\n");
		buf.append("document.markForm.courseId.selectedIndex = 0;\n");

		buf.append("document.markForm.studentId.length = " + 1 + ";\n");
		buf.append("document.markForm.studentId.options[0].value = '';\n");
		buf.append("document.markForm.studentId.options[0].text = '--请先选择班级--';\n");
		buf.append("document.markForm.studentId.selectedIndex = 0;\n");
		
		for(int i=0; i<courses.size(); i++) {
			Course c = courses.get(i);
			buf.append("document.markForm.courseId.options[" + (i+1) + "].value = '" + c.getId() + "';\n");
			buf.append("document.markForm.courseId.options[" + (i+1) + "].text = '" + c.getName() + "';\n");
		}
	}
	
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-store"); //HTTP1.1
	response.setHeader("Pragma", "no-cache"); //HTTP1.0
	response.setDateHeader("Expires", 0); //prevents catching at proxy server
	
	response.getWriter().write(buf.toString());
%>
