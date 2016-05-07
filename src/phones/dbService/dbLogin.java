package phones.dbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import phones.domain.*;

public class dbLogin {

	private Connection conn;
	private PreparedStatement prepStmt;
	private ResultSet queryResult;
	private boolean clientExists = false;
	private clientsInfo clientInfo;
	
//	public static void main(String[] args) {
//		dbLogin db = new dbLogin();
//		clientsInfo clientInfo = null;
//		try{
//			db.createConnection("root", "moon");
//			db.getClientInfoByEmail("aman.altay@gmail.com");
//			//clientInfo = db.getClientInfo();
//			db.closeConnection();
//		} catch(SQLException e) {
//			System.out.println("Exception: " + e.getMessage() + 
//					  " Thrown by: " + e.getClass().getSimpleName());
//		}
//
//		clientInfo = db.getClientInfo();
//		System.out.println("Executed" + clientInfo.getClientFirstName());
//	}
	
	
	//=================================================================================
	
		public void createDefaultConnection() throws SQLException{
			if(conn == null)
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones_db","root","moon");
		}
		
		public void createConnection(String user, String password) throws SQLException{
			if(conn == null)
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones_db", user, password);
		}
		
		public void closeConnection() throws SQLException{
			if(conn != null)
				conn.close();
		}
		
		public boolean clientExists() {
			return clientExists;
		}
		
		public clientsInfo getClientInfo() {
			return clientInfo;
		}
		
		public void getClientInfoByEmail(String email) throws SQLException{
			String stmt = "SELECT * FROM CLIENTS_INFO WHERE client_email = ?";
			prepStmt = conn.prepareStatement(stmt);
			prepStmt.setString(1, email);
			
			queryResult = prepStmt.executeQuery();
			
			int counter = 0;
			clientInfo = new clientsInfo();
			while(queryResult.next()) {
				int client_id = queryResult.getInt("client_id");
				String client_firstname = queryResult.getString("client_firstname");
				String client_lastname = queryResult.getString("client_lastname");
				String client_email = queryResult.getString("client_email");
				
				clientInfo.setClientId(client_id);
				clientInfo.setClientFirstName(client_firstname);
				clientInfo.setClientLastName(client_lastname);
				clientInfo.setClientEmail(client_email);
				
				counter++;
			}
		}
		
		public void clientAuthentication(String email, String password) throws SQLException {
			String stmt = "SELECT * FROM CLIENTS_PASSWORDS WHERE client_email = ? AND client_password = ?";
			
			prepStmt = conn.prepareStatement(stmt);
			prepStmt.setString(1, email);
			prepStmt.setString(2, password);
			
			queryResult = prepStmt.executeQuery();
			
			int counter = 0; 
			while(queryResult.next()) {
				counter++;
			}
		
			
			if(counter == 1) {
				clientExists = true;
				getClientInfoByEmail(email);
			}
			
			prepStmt.close();
			queryResult.close();
			
		}
		
	
}
