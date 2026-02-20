package postcallrequests;

public class UserPOJOClass {
	

	private String name;
	private String gender;
	private String status;
	private String email;
	
	
	//default constructor
	
	public UserPOJOClass() {}
	
	
	//parameterized constructor
	public UserPOJOClass(String name, String gender, String status, String email) {
		this.name = name;
		this.gender = gender;
		this.status = status;
		this.email = email;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	
	
	
	
	
	
	
	
	
	

}
