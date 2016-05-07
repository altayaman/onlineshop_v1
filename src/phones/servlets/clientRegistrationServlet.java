package phones.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import phones.dbService.dbRegistration;



//@WebServlet("/SignUpServletPath")
public class clientRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//request.getRequestDispatcher("link.html").include(request, response);
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		dbRegistration registration = new dbRegistration();
		boolean isEmailExists = false;
		String registrationStatusMessage = "";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			registration.createConnection("root", "moon");
			isEmailExists = registration.isEmailExists(email);
			
			if(isEmailExists) {
				registrationStatusMessage = "Sorry, email you entered already exists in our database";
			}
			else {
				registrationStatusMessage = "Welcome " + firstName + ", you are registered successfully";
				registration.insertClientInfo(firstName, lastName, email, password);
			}
			
			registration.closeConnection();
		} catch(SQLException e) {
			System.out.println("Exception: " + e.getMessage() + 
					  " Thrown by: " + e.getClass().getSimpleName());
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage() + 
					  " Thrown by: " + e.getClass().getSimpleName());
		}

	
		RequestDispatcher clientRegistrationDispatcher = request.getRequestDispatcher("clientRegistrationStatus.jsp");
		request.setAttribute("registrationStatusMessage", registrationStatusMessage);
		clientRegistrationDispatcher.forward( request, response );
		
	}

}

