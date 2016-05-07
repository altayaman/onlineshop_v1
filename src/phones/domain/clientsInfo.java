package phones.domain;

public class clientsInfo {
	
	private int client_id;
	private String client_firstname;
	private String client_lastname;
	private String client_email;
	
	public static void main(String[] args){}
	
	public clientsInfo() {
	}
	
	public clientsInfo(int client_id, String client_firstname, String client_lastname, String client_email) {
		this.client_id = client_id;
		this.client_firstname = client_firstname;
		this.client_lastname = client_lastname;
		this.client_email = client_email;
	}
	
	public int getClientId() {
		return client_id;
	}
	
	public String getClientFirstName() {
		return client_firstname;
	}
	
	public String getClientLastName() {
		return client_lastname;
	}
	
	public String getClientEmail() {
		return client_email;
	}
	
	public void setClientId(int client_id) {
		this.client_id = client_id;
	}
	
	public void setClientFirstName(String client_firstname) {
		this.client_firstname = client_firstname;
	}
	
	public void setClientLastName(String client_lastname) {
		this.client_lastname = client_lastname;
	}
	
	public void setClientEmail(String client_email) {
		this.client_email = client_email;
	}
	
	public String toString() {
		return "" + client_id + ", " + client_firstname + ", " + client_lastname + ", " + client_email; 
	}

}
