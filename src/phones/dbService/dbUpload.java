package phones.dbService;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Part;

public class dbUpload {
	
	private Connection conn;
	private PreparedStatement prepStmt;
	private ResultSet queryResult;
	
	public boolean phones_info_affected = false;
	public boolean phones_images_affected = false;
	
	//public static void main(String[] args){}
	
	//=================================================================================
	
	public void createDefaultConnection() throws SQLException{
		if(conn == null) {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones_db","root","moon");
			conn.setAutoCommit(false);
		}
	}
	
	public void createConnection(String user, String password) throws SQLException{
		if(conn == null) {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones_db", user, password);
			conn.setAutoCommit(false);
		}
	}
	
	public void closeConnection() throws SQLException{
		if(conn != null) {
			conn.commit();
			prepStmt.close();
			queryResult.close();
			conn.close();
		}
	}
	
	public void uploadPhoneInfo(int client_id, String phone_brand, String phone_condition)  throws SQLException {
		String stmt = "INSERT INTO PHONES_INFO (client_id, phone_brand, phone_condition) VALUES (?, ?, ?)";
		prepStmt = conn.prepareStatement(stmt);
		
		prepStmt.setInt(1, client_id);
		prepStmt.setString(2, phone_brand);
		prepStmt.setString(3, phone_condition);
		
		int affectedRows = prepStmt.executeUpdate();
		
		if(affectedRows > 0)
			phones_info_affected = true;
		
	}
	
	public void uploadPhoneImage(int client_id, Part filePart) throws SQLException {
		InputStream inputStream = null;
		
		String stmt = "INSERT INTO PHONES_IMAGES (CLIENT_ID, PHONE_IMAGE) VALUES (?, ?)";
		prepStmt = conn.prepareStatement(stmt);
		
		prepStmt.setInt(1, client_id);
		
		if (filePart != null) 
        {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            try{
            inputStream = filePart.getInputStream();
            } catch(Exception e) {
            	System.out.println("Java class part Exception #1: " + e.getMessage() + 
  					  " Thrown by: " + e.getClass().getSimpleName());
            }
            
            prepStmt.setBinaryStream(2, inputStream, (int) filePart.getSize());
        }
		
		int affectedRows = prepStmt.executeUpdate();
		
		if(affectedRows > 0)
			phones_info_affected = true;
		
	}
	
}
