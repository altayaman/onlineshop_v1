package phones.dbService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import phones.domain.clientsInfo;

public class dbGetContent {

	private Connection conn;
	private PreparedStatement prepStmt;
	private ResultSet queryResult;
	
	public static void main(String[] args) {
		dbGetContent db = new dbGetContent();
		ArrayList<clientsInfo> clientsList = new ArrayList<clientsInfo>();
		JSONArray jsonArray = new JSONArray();
		
		try{
			db.createConnection("root", "moon");
			clientsList = db.getClientsInfo();
			jsonArray = db.getClientsInfoJSON();
			db.closeConnection();
		} catch(SQLException e) {
			System.out.println("Exception: " + e.getMessage() + 
					  " Thrown by: " + e.getClass().getSimpleName());
		}
		
		for(clientsInfo s: clientsList){
			String st = s.toString();
			System.out.println(st);
		}
		
		
		try{
			System.out.println(jsonArray.getJSONObject(0).get("client_id"));
			System.out.println(jsonArray.getJSONObject(1).get("client_firstname"));
		} catch(JSONException e) {
		}
		
	}
	
	
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
		
		public ArrayList<clientsInfo> getClientsInfo() throws SQLException {
			String stmt = "SELECT * FROM CLIENTS_INFO";
			
			prepStmt = conn.prepareStatement(stmt);
			queryResult = prepStmt.executeQuery();
			
			clientsInfo clientInfo; 
			ArrayList<clientsInfo> clientsList = new ArrayList<clientsInfo>();
			
			while(queryResult.next()) {
				int client_id = queryResult.getInt("client_id");
				String client_firstname = queryResult.getString("client_firstname");
				String client_lastname = queryResult.getString("client_lastname");
				String client_email = queryResult.getString("client_email");
				
				clientInfo = new clientsInfo(client_id, client_firstname, client_lastname, client_email);
				clientsList.add(clientInfo);
			}

			
			prepStmt.close();
			queryResult.close();
			
			return clientsList;
		}
		
		
		public JSONArray getClientsInfoJSON() throws SQLException {
			
			JSONArray jsonArray = new JSONArray();
			JSONObject json;
			
			String stmt = "SELECT * FROM CLIENTS_INFO";
			
			prepStmt = conn.prepareStatement(stmt);
			queryResult = prepStmt.executeQuery();
			
			
			while(queryResult.next()) {
				int client_id = queryResult.getInt("client_id");
				String client_firstname = queryResult.getString("client_firstname");
				String client_lastname = queryResult.getString("client_lastname");
				String client_email = queryResult.getString("client_email");
				
				try{
					json = new JSONObject();
					
					json.put("client_id", client_id);
					json.put("client_firstname", client_firstname);
					json.put("client_lastname", client_lastname);
					json.put("client_email", client_email);
					
					jsonArray.put(json);
					
				} catch(JSONException e) {
					System.out.println("Exception: " + e.getMessage() + 
							  " Thrown by: " + e.getClass().getSimpleName());
				}
				
				
			}

			
			prepStmt.close();
			queryResult.close();
			
			return jsonArray;
		}

		
}
