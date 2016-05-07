package phones.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import phones.dbService.dbUpload;



//@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)

public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session=request.getSession(false);  
        
        int clientId = (int) session.getAttribute("clientId"); 
        String phoneBrand = request.getParameter("phoneBrand");
        String phoneCondition = request.getParameter("phoneCondition");
        Part filePart = null;
        filePart = request.getPart("uploadFile");
            
               
        dbUpload up = new dbUpload();
        String uploadStatusMessage = "";
        
        try{
			Class.forName("com.mysql.jdbc.Driver");
			up.createConnection("root", "moon");
			up.uploadPhoneInfo(clientId, phoneBrand, phoneCondition);
			up.uploadPhoneImage(clientId, filePart);
			up.closeConnection();
			
		} catch(SQLException e) {
			System.out.println("Servlet part Exception #1: " + e.getMessage() + 
					  " Thrown by: " + e.getClass().getSimpleName());
		} catch(Exception e) {
			System.out.println("Servlet part Exception #2: " + e.getMessage() + 
					  " Thrown by: " + e.getClass().getSimpleName());
		}        
        

        if(up.phones_info_affected == true && up.phones_images_affected == true) {
        	uploadStatusMessage = "Phone information and image are uploaded";
        }
        else if(up.phones_images_affected == true) {
        	uploadStatusMessage = "Phone image is uploaded";
        }
        else if(up.phones_info_affected == true) {
        	uploadStatusMessage = "Phone information is uploaded";
        }
        
        RequestDispatcher uploadDispatcher = request.getRequestDispatcher("phoneInfoUploadStatus.jsp");
		request.setAttribute("uploadStatusMessage", uploadStatusMessage);
		uploadDispatcher.forward( request, response );

	}   
}