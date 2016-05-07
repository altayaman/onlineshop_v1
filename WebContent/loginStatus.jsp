<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login status page</title>
	</head>

	<h2>Hi <%=request.getAttribute("clientName")%>, you are logged in successfully</h2>

	<body>
	<!--form action = "logoutPath" method = "POST" -->
	
	<a href="phoneRegistration.jsp">Register phone info for sale</a><p>
	<a href="dbContent.jsp">Get sellers list</a><p>
	<a href="logout.jsp">Logout</a><p>
	</body>
</html>