package logic;

import java.util.HashSet;
import java.util.Set;

import ORM.TaskMapper;

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
	protected TaskMapper mapper = new TaskMapper ();
	
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
	 * User info to pass to UI
	 * 
	 * @return	UIUser class instance
	 */
    public UIUser getUIUser() {
    	return new UIUser(this.firstName, this.lastName);
    }
    
	/**
	 * Initialize creating new task process
	 * 
	 * @param task	Info about task
	 * @return		List of all resources
	 */
    public Set<UIResource> createTask(UITask task) {
    	this.currentTask = new Task(task);
    	return currentTask.getAllResources();
    	//TODO: don't forget to add current task to userTasks when saving a task
    	//TODO: add task creator
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
     * Complete creating task
     */
	public void acceptTask() {
		currentTask.prepareResources();
		userTasks.add(currentTask);
    	//TODO: check dependencies while saving
		mapper.setTask(currentTask);
    }
    
	/**
	 * Get list of all tasks
	 * 
	 * @return	List of all tasks
	 */
	public Set<UITask> getAllTasks()
	{
		allTasks = mapper.getAllTasks();
		mapper.getAllTasksById(this);
		Set<UITask> uitasks = new HashSet<UITask>();
		for(Task t: allTasks) {
			uitasks.add(t.getUITask());
		}
		return uitasks;
	}
	
	/**
	 * Get list of tasks for specified dates
	 * 
	 * @param uidate	Dates
	 * @return			List of tasks
	 */
	public Set<UITask> getAllTasksForDates(UIDates uidate)
	{
		//TODO: add some flag for UI for tasks, created by user
		Dates date = new Dates (uidate);
		allTasks = mapper.getAllTasksForDates(date);
		mapper.getAllTasksById(this);
		Set<UITask> uitasks = new HashSet<UITask>();
		for(Task t: allTasks) {
			uitasks.add(t.getUITask());
		}
		return uitasks;
	}
	
	/**
	 * Initialize modifying a task process
	 * 
	 * @return	 true in case current user is the owner of selected task
	 */
	public boolean modifyTask (UITask uitask)
	{
		for(Task t: userTasks) {
			if(t.equals(uitask)) {
				this.currentTask=t;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Edit task to resources assignment
	 * 
	 * @param addedResources	List of added resources
	 * @param removedResources	List of removed resources
	 * @return					Conflict dates
	 */
	public Set<UIDates> modifyResources(Set<UIResource> addedResources, Set<UIResource> removedResources) 
	{
		return currentTask.modifyResources(addedResources, removedResources);
	}
	
	/**
	 * Edit task participants
	 * 
	 * @param addedUsers	List of added users
	 * @param removedUsers	List of removed users
	 */
	public void modifyUsers(Set<User> addedUsers, Set<User> removedUsers) {
		currentTask.modifyUsers(addedUsers, removedUsers);
	}
	
	/**
	 * Edit task dates, period or length in minutes
	 * Deletes everything except name and capacity and starts use case "create task"
	 * 
	 * @param uitask	Task info
	 */
	public void modifyDates(UITask uitask) {
		currentTask.modifyDates(uitask);
	}
	
	/**
	 * Edit task name and capacity
	 * 
	 * @param uitask	Task info
	 */
	public void modifyInfo(UITask uitask) {
		currentTask.setName(uitask.getName());
		currentTask.setCapacity(uitask.getCapacity());
	}
	
	/**
	 * Get task info: dates
	 * 
	 * @return	List of dates
	 */
	public Set<UIDates> getTaskDates()
	{
		return currentTask.getTaskDates();
	}
	
	/**
	 * Get task info: resources
	 * 
	 * @return	List of resources
	 */
	public Set<UIResource> getTaskResources()
	{
		return currentTask.getTaskResources();
	}
	
	/**
	 * Get task info: users
	 * 
	 * @return	List of users
	 */
	public Set<UIUser> getTaskUsers()
	{
		return currentTask.getTaskUsers();
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
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
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
