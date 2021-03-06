package uiclasses;

/*
 * UI->BL class for creating request for registration
 * Use case: create new user
 */
public class UIRequest {
	protected String firstName;
	protected String lastName;
	protected String login;
	protected String password;
	protected String email;
	protected String job;
	//TODO: birthday and sex
	
	public UIRequest(String firstName, String lastName, String login,
			String password, String email, String job) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.job = job;
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

	@Override
	public String toString() {
		return "UIRequest [firstName=" + firstName + ", lastName=" + lastName
				+ ", login=" + login + ", password=" + password + ", email="
				+ email + ", job=" + job + "]";
	}
}
