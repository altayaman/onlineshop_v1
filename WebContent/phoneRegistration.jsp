<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Phone register page</title>
    </head>
    
    
    
    <body>
      
        <form method="post" action="uploadPath" enctype="multipart/form-data">
            <center>
                <table border="1" width="40%" cellpadding="8">
                    <thead> 
                            <th colspan="3">Upload File</th>        
                    </thead>
                    <tbody>
	                    <tr>
		                    <td>
		                    	Select phone brand :
		                    </td>
	                    	<td>
	                    		<select name = "phoneBrand">
									<option value = "Nokia">Nokia</option>
									<option value = "Samsung">Samsung</option>
									<option value = "LG">LG</option>
									<option value = "IPhone">IPhone</option>
								</select>
	                    	</td>
	                    </tr>
                        <tr>
                            <td>Choose File : </td>
                            <td><input type="file" name="uploadFile" /></td>
                        </tr>
                        <tr>
                        	<td>
                        		Phone condition: 
                        	</td>
                        	<td>
                        		<input type = "radio" name = "phoneCondition" value = "New"> New </input>
								<input type = "radio" name = "phoneCondition" value = "Used"> Used </input>
                        	</td>
                        </tr>
                        <tr>
                            <td colspan="3"><center><input type="submit" value="Upload"></center></td>
                        </tr>
                    </tbody>             
                </table><p>
            </center>
        </form>
        <a href="logout.jsp">Logout</a>
    </body>
    
</html>
