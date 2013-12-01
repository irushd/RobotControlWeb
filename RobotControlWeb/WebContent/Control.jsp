<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="RobotMovement.UserMovement"%>
<%@page import="Database.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Control the Robot</title>
</head>
<body>
	<%String movement = "Stopped";
	String left="",right="",forward="",back="";
	UserMovement usrMov = new UserMovement();
	%>
	<% 
		if(session.isNew() || (session.getAttribute("userObj")==null)){
			%>
	<jsp:forward page="/index.html">
		<jsp:param name="cause" value="Invalid session" />
	</jsp:forward>
	
	<%
	} else{ 
		User userObj = (User)session.getAttribute("userObj");
		left = userObj.getLeft();
		right = userObj.getRight();
		forward = userObj.getForward();
		back = userObj.getBack();
	} 
	%>


	<%
	if(request.getParameter("Left") != null){
		movement = "Going Left";
		usrMov = new UserMovement();
		UserMovement.setCommand(left);
		usrMov.start();
	}
	if(request.getParameter("Right") != null){
		movement = "Going right";
		usrMov = new UserMovement();
		UserMovement.setCommand(right);
		usrMov.start();
	}
	if(request.getParameter("Forward") != null){
		movement = "Going Forward!";
		usrMov = new UserMovement();
		UserMovement.setCommand(forward);
		usrMov.start();
	}
	if(request.getParameter("Stop") != null){
		movement = "Going Nowhere";
		usrMov = new UserMovement();
		UserMovement.setCommand("s");
		usrMov.start();
	}
	if(request.getParameter("Back") != null){
		movement = "Going Back";
		usrMov = new UserMovement();
		UserMovement.setCommand(back);
		usrMov.start();
	}
	%>

	<p style="text-align: center;">
		Movement:&nbsp;<strong><%=movement%>&nbsp;</strong>
	</p>
	<form action="Control.jsp" method="post">
		<p style="text-align: center;">
			<input name="Forward" type="submit" value="Go Forward!" />
		</p>
		<p style="text-align: center;">
			<input name="Left" type="submit" value="Go Left" /><input
				name="Stop" type="submit" value="Stop" /> <input name="Right"
				type="submit" value="Go Right" />
		</p>
		<p style="text-align: center;">
			<input name="Back" type="submit" value="Reverse" />
		</p>
	</form>
	<p>Status:&nbsp;</p>
	<br>

</body>
</html>