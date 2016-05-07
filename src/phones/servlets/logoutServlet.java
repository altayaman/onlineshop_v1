package phones.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter(); 
		
        //request.getRequestDispatcher("link.html").include(request, response);
        
        response.setHeader("Cache-Control", "no-cache, no-store");
    	response.setHeader("Pragma", "no-cache");
        
		HttpSession session=request.getSession(false);  
        session.invalidate();  
        response.sendRedirect(request.getContextPath() + "/login.jsp");  
        
        out.print("You are logged out successfully !");  
        out.close();
	}

}
