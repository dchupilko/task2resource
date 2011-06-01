package logic;

import java.util.HashSet;
import java.util.Set;

import uiclasses.*;

public class User {
	protected int oid;
	protected int version;
	
	protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected String password;

	protected Task currentTask;
    protected Set<Task> tasks = new HashSet<Task>();		// all tasks in which user takes part
    protected Set<Task> userTasks = new HashSet<Task>();    // all tasks created by user
    protected Set<Task> allTasks = new HashSet<Task>();		// list of all existing tasks
    
	public User() {}

	public User(UIRequest uirequest) {
		this.firstName = uirequest.getFirstName();
		this.lastName = uirequest.getLastName();
		this.email = uirequest.getEmail();
		this.login = uirequest.getLogin();
		this.password = uirequest.getPassword();
	}

	
	// M E T H O D S
	
	/**
	 * Use case: creating new task.
	 * 
	 * @param task	Info about task
	 * @return		List of all resources
	 */
    public Set<UIResource> createTask(UITask task) {
    	this.currentTask = new Task(task);
    	return currentTask.getAllResources();
    	//TODO: don't forget to add current task to userTasks when saving a task
    }

    /**
     * Assign selected resources to dates.
     * 
     * @param resources	List of selected resources
     * @return			Dates of conflicts
     */
    public Set<UIDates> chooseResources(Set<UIResource> resources) {
    	return currentTask.chooseResources(resources);
    }
    
    /**
     * Find replacement for a conflict
     * 
     * @param date	Conflict date
     * @return		List of available resources
     */
    public Set<UIResource> resolveConflict(UIDates date) {
    	return currentTask.getResourcesForDate(date);
    }
    
    /**
     * Assign selected resources to specified date
     * 
     * @param resources	List of selected resources
     * @param date		Date of assignment
     */
    public void chooseResourcesForDate(Set<UIResource> resources, UIDates date) {
    	currentTask.chooseResourcesForDate(resources, date);
    }

    /**
     * Assign users to current task
     * 
     * @param users	List of selected users
     */
    public void assignUsers(Set<User> users) {
    	currentTask.assignUsers(users);
    }

	/**
	 * User name to pass to UI.
	 * 
	 * @return	UIUser class instance
	 */
    public UIUser getUIUser() {
    	return new UIUser(this.firstName, this.lastName);
    }
	
    /**
     * Complete creating task
     */
	public void acceptTask() {
    	//TODO: save task
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Task> getUserTasks() {
		return userTasks;
	}

	public void setUserTasks(Set<Task> userTasks) {
		this.userTasks = userTasks;
	}
}
