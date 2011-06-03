package logic;

import uiclasses.*;

public class Request {
	protected int oid;
	protected int version;
	
	protected String firstName;
	protected String lastName;
	protected String login;
	protected String password;
	protected String email;
	protected String job;
	
	public Request() {}
	
	public Request(UIRequest request) {
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.login = request.getLogin();
		this.password = request.getPassword();
		this.email = request.getEmail();
		this.job = request.getJob();
	}

	
	// M E T H O D S
	
    /**
	 * Request info to pass to UI.
	 * 
	 * @return	UIRequest class instance
	 */
	public UIRequest getUIRequest() {
		return new UIRequest(firstName, lastName, login, password, email, job);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Request other = (Request) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	
	// A C C E S S O R S
	
	public int getOid() {
		return oid;
	}
	
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
