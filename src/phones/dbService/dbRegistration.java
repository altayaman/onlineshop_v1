package phones.dbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbRegistration {

	private Connection conn;
	private PreparedStatement prepStmt;
	private ResultSet queryResult;
	
//	public static void main(String[] args) {
//		dbRegistration db = new dbRegistration();
//		try{
//			db.createConnection("root", "moon");
//			db.insertClientInfo("Al", "Aman", "email", "moon");
//			db.closeConnection();
//		} catch(SQLException e) {
//			System.out.println("Exception: " + e.getMessage() + 
//					  " Thrown by: " + e.getClass().getSimpleName());
//		}
//		System.out.println("Executed");
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
		
		public boolean isEmailExists(String email) throws SQLException {
			boolean exists = false;
			
			String check_email = "SELECT CLIENT_EMAIL FROM CLIENTS_INFO WHERE CLIENT_EMAIL = ?";
			prepStmt = conn.prepareStatement(check_email);
			prepStmt.setString(1, email);
			
			queryResult = prepStmt.executeQuery();
			int count = 0;
			while(queryResult.next()) {
				count++;
			}
			
			if(count > 0)
				exists = true;
			
			return exists;
		}
		
		public void insertClientInfo(String fname, String lname, String email, String password) throws SQLException {
			String insert_into_clients_info_tbl = "INSERT INTO CLIENTS_INFO (CLIENT_FIRSTNAME, CLIENT_LASTNAME, CLIENT_EMAIL) VALUES (?, ?, ?)";
			String get_client_id = "SELECT CLIENT_ID FROM CLIENTS_INFO WHERE CLIENT_FIRSTNAME = ? AND CLIENT_LASTNAME = ? AND CLIENT_EMAIL = ?";
			String insert_into_clients_passwords_tbl = "INSERT INTO CLIENTS_PASSWORDS (CLIENT_ID, CLIENT_EMAIL, CLIENT_PASSWORD) VALUES (?, ?, ?)";
			
			prepStmt = conn.prepareStatement(insert_into_clients_info_tbl);
			prepStmt.setString(1, fname);
			prepStmt.setString(2, lname);
			prepStmt.setString(3, email);
			prepStmt.executeUpdate();
			
			prepStmt = conn.prepareStatement(get_client_id);
			prepStmt.setString(1, fname);
			prepStmt.setString(2, lname);
			prepStmt.setString(3, email);
			queryResult = prepStmt.executeQuery();
			int clientId = 0;
			while(queryResult.next()) {
				clientId = queryResult.getInt("client_id");
			}
			
			prepStmt = conn.prepareStatement(insert_into_clients_passwords_tbl);
			prepStmt.setInt(1, clientId);
			prepStmt.setString(2, email);
			prepStmt.setString(3, password);
			prepStmt.executeUpdate();
			
		}
		
		public void getQueryResult(String query) throws SQLException{
			
			prepStmt = conn.prepareStatement(query);
			
			queryResult = prepStmt.executeQuery();
			
			String userName;
			String userPasscode;
			System.out.println("UserName   UserPasscode");
			
			while(queryResult.next()) {
				userName = queryResult.getString("UserName");
				userPasscode = queryResult.getString("UserPasscode");
				System.out.println(userName + "  " + userPasscode);
			}
		
			prepStmt.close();
			queryResult.close();
		}
}
