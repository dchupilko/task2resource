package logic;

import java.util.HashSet;
import java.util.Set;
import uiclasses.*;

public class User {
	protected int oid;
	protected String firstName;
    protected String lastName;
    protected String email;
    protected Task currentTask;
    protected Set<Task> tasks = new HashSet<Task>();
    
    public User() {}

    public Set<UIResource> createTask(UITask task) {
    	this.currentTask = new Task(task);
    	return currentTask.getAllResources();
    }
    
    public Set<UIDates> chooseResources(Set<UIResource> resources) {
    	return currentTask.chooseResources(resources);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}