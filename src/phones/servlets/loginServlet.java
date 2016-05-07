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
import javax.servlet.http.HttpSession;

import phones.dbService.dbLogin;
import phones.domain.clientsInfo;

//@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//request.getRequestDispatcher("link.html").include(request, response);
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		dbLogin login = new dbLogin();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			login.createConnection("root", "moon");
			login.clientAuthentication(email, password);
			login.closeConnection();
			
		} catch(SQLException e) {
			System.out.println("Exception 1: " + e.getMessage() + 
					  " Thrown by: " + e.getClass().getSimpleName());
		} catch(Exception e) {
			System.out.println("Exception 2: " + e.getMessage() + 
					  " Thrown by: " + e.getClass().getSimpleName());
		}
		
		HttpSession session;
		clientsInfo clientInfo;
		if(email == "" || password == "")
			out.println("One or both fields are empty");
		
		else if(login.clientExists() == true) {
			clientInfo = login.getClientInfo();
			
			session = request.getSession();  
	        session.setAttribute("clientId",clientInfo.getClientId());
	        session.setAttribute("clientName",clientInfo.getClientFirstName());
			
			RequestDispatcher loginStatusDispatcher = request.getRequestDispatcher("loginStatus.jsp");
			request.setAttribute("clientName", clientInfo.getClientFirstName());
			loginStatusDispatcher.forward( request, response );
			
		}
		else {
			out.println("Sorry, access denied");
			out.println("You entered wrong username/password ");
		}
	}

}
