package logic;

import uiclasses.UIRequest;

public class Request {
	protected int oid;
	
	protected String firstName;
	protected String lastName;
	protected String login;
	protected String password;
	protected String email;
	protected String job;
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	protected int version;
	
	public Request() {}
	
	public Request(UIRequest request) {
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.login = request.getLogin();
		this.password = request.getPassword();
		this.email = request.getEmail();
		this.job = request.getJob();
	}
	
	public int getOid() {
		return oid;
	}
	
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
}
