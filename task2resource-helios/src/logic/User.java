package logic;

import java.util.HashSet;
import java.util.Set;
import uiclasses.*;

public class User {
	protected int oid;
	protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected String password;
    protected Group group = null;

	protected Task currentTask = null;
	//all tasks in which user takes part
    protected Set<Task> tasks = new HashSet<Task>();
    //all tasks created by user
    protected Set<Task> userTasks = new HashSet<Task>();
    protected int version;
    
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
	
	public Group getGroup(){
		return group;
	}
	
	public void setGroup(Group gr){
		this.group = gr;
	}

   
    public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public User() {}

    public Set<UIResource> createTask(UITask task) {
    	this.currentTask = new Task(task);
    	return currentTask.getAllResources();
    	//TODO: don't forget to add current task to userTasks when saving a task
    }
        
    public Set<UIDates> chooseResources(Set<UIResource> resources) {
    	return currentTask.chooseResources(resources);
    	//TODO: resolve conflicts
    }
    
    public Set<UIResource> resolveConflict(UIDates date) {
    	return currentTask.getResourcesForDate(date);
    }
    
    public void chooseResourcesForDate(Set<UIResource> resources, UIDates date) {
    	currentTask.chooseResourcesForDate(resources, date);
    }
    
    public void assignUsers(Set<UIUser> users) {
    	currentTask.assignUsers(users);
    }
    
    public void acceptTask() {
    	//TODO: save task
    }
    
    public Set<UIGroup> getAllGroups () {
    	return currentTask.getAllGroups();
    }
    
    public Set<Task> getUserTasks() {
		return userTasks;
	}

	public void setUserTasks(Set<Task> userTasks) {
		this.userTasks = userTasks;
	}

	public Set<UIUser> getAllUsers(UIGroup uigroup) {
    	return currentTask.getAllUsers(uigroup);
    }
    
	public UIUser getUIUser() {
    	return new UIUser(this.firstName, this.lastName);
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
