<%@page import="phones.dbService.dbGetContent"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="org.json.JSONArray"%>
<%@page import="phones.domain.clientsInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Database content retrieval</title>
	</head>
	
	<h1> List of sellers: </h1>

	<body>
	<%
		dbGetContent dbContent = new dbGetContent();
		ArrayList<clientsInfo> clientsList = new ArrayList<clientsInfo>();
		
		JSONArray jsonArray = new JSONArray();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			dbContent.createConnection("root", "moon");
			clientsList = dbContent.getClientsInfo();
			jsonArray = dbContent.getClientsInfoJSON();
			dbContent.closeConnection();
		} catch(SQLException e) {
			System.out.println("Exception: " + e.getMessage() + 
					  " Thrown by: " + e.getClass().getSimpleName());
		}
		
		
		for(int i = 0; i < jsonArray.length(); i++) {
			
	%>
	
		<h4>
			<%= jsonArray.getJSONObject(i).get("client_id")%>
			<%= jsonArray.getJSONObject(i).get("client_firstname")%>
			<%= jsonArray.getJSONObject(i).get("client_lastname")%>
			<%= jsonArray.getJSONObject(i).get("client_email")%>
		</h4>
	
	<%} %>
	
	<p>
	<a href="logout.jsp">Logout</a>
	</body>
</html>