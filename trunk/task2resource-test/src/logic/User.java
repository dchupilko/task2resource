package logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import ORM.TaskMapper;
import ORM.UserMapper;

import uiclasses.*;

public class User {
	
	private static final Logger log = Logger.getLogger(User.class);
	
	protected int oid;
	protected int version;
	
	protected String firstName;
    protected String lastName;
    protected String email;
    protected String login;
    protected String password;
    
    protected boolean assigned = false;

	protected Task currentTask;
	protected TaskMapper mapper = new TaskMapper ();
	
    protected Set<Task> tasks = new HashSet<Task>();		// all tasks in which user takes part
    protected Set<Task> userTasks = new HashSet<Task>();    // all tasks created by user
    protected Set<Task> allTasks = new HashSet<Task>();		// list of all existing tasks
    
	public User() {}

	public User(UIRequest uirequest) {
		log.debug("Constructing from UIRequest");
		log.debug(uirequest.getLogin());
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
    	log.debug("Getting UIUser");
    	return new UIUser(this.firstName, this.lastName, this.assigned);
    }
    
	/**
	 * Initialize creating new task process
	 * 
	 * @param task	Info about task
	 * @return		List of all resources
	 */
    public void createTask() {
    	log.debug("Creating task");
    	this.currentTask = new Task();
    	//TODO: don't forget to add current task to userTasks when saving a task
    	//TODO: add task creator
    }
    
    public void setTaskInfo (UITask task)
	{
    	currentTask.setTaskInfo(task);
	}
	  
    public Set<UIResource> getAllResources ()
    {
    	return currentTask.getAllResources(this.getOid());
    }

    /**
     * Assign selected resources to dates.
     * 
     * @param resources	List of selected resources
     * @return			Dates of conflicts
     */
    public Set<UIDates> chooseResources(Set<UIResource> resources) {
    	log.debug("Choosing resources from set");
    	return currentTask.chooseResources(resources);
    }
    
    /**
     * Find replacement for a conflict
     * 
     * @param date	Conflict date
     * @return		List of available resources
     */
    public Set<UIResource> resolveConflict(UIDates date) {
    	log.debug("Resolving conflicts to date " + date.getFinishDate().getTime());
    	return currentTask.getResourcesForDate(date);
    }
    
    /**
     * Assign selected resources to specified date
     * 
     * @param resources	List of selected resources
     * @param date		Date of assignment
     */
    public void chooseResourcesForDate(Set<UIResource> resources, UIDates date) {
    	log.debug("Choosing set of resources for date " + date.getStartDate().getTime() +
    			" " + date.getFinishDate().getTime());
    	currentTask.chooseResourcesForDate(resources, date);
    }

    /**
     * Assign users to current task
     * 
     * @param users	List of selected users
     */
    public void assignUsers(Set<User> users) {
    	log.debug("Assigning set of users");
    	currentTask.assignUsers(users);
    }
	
    /**
     * Complete creating task
     */
	public void acceptTask() {
		log.debug("Accepting task");
		currentTask.prepareResources();
		mapper.getAllTasksById(this);
		userTasks.add(currentTask);
		mapper.setTask(currentTask);	
		log.debug(currentTask.getName() + " " + currentTask.getDescription());
		(new UserMapper()).updateUser(this);
    }
    
	/**
	 * Get list of all tasks
	 * 
	 * @return	List of all tasks
	 */
	public List<UITask> getAllTasks(){
		log.debug("Getting all tasks");
		allTasks = mapper.getAllTasks(this);
		mapper.getAllTasksById(this);
		List<UITask> uitasks = new ArrayList<UITask>();
		for(Task t: allTasks) {
			uitasks.add(t.getUITask());
			log.debug(t.getUITask().getName());
		}
		return uitasks;
	}
	
	public List<UITask> getAllUserTasks(){
		log.debug("Getting all user tasks");
		if(userTasks.size()==0)
		{
			allTasks = mapper.getAllTasks(this);
			mapper.getAllTasksById(this);
		}		
		List<UITask> uitasks = new ArrayList<UITask>();
		for(Task t: userTasks) {
			uitasks.add(t.getUITask());
			log.debug(t.getUITask().getName());
		}
		return uitasks;
	}
	
	/**
	 * Get list of tasks for specified dates
	 * 
	 * @param uidate	Dates
	 * @return			List of tasks
	 */
	public Set<UITask> getAllTasksForDates(UIDates uidate){
		//TODO: add some flag for UI for tasks, created by user
		log.debug("Getting all tasks for dates" + uidate.getStartDate().getTime());
		Dates date = new Dates (uidate);
		allTasks = mapper.getAllTasksForDates(date);
		mapper.getAllTasksById(this);
		Set<UITask> uitasks = new HashSet<UITask>();
		for(Task t: allTasks) {
			uitasks.add(t.getUITask());
			log.debug(t.getUITask().getName());
		}
		return uitasks;
	}
	
	/**
	 * Initialize modifying a task process
	 * 
	 * @return	 true in case current user is the owner of selected task
	 */
	public boolean modifyTask (UITask uitask){
		log.debug("Modifying task " + uitask.getName());
		for(Task t: userTasks) {
			if(t.getName().equals(uitask.getName())
					&&t.getFromDate().equals(uitask.getFromDate())
					&&t.getToDate().equals(uitask.getToDate())) 
			{
				this.currentTask=t;
				this.currentTask.setStatus();
				log.debug("Modifying complete");
				return true;
			}
		}
		log.debug("Modifying did not complete");
		return false;
	}
	

	/**
	 * Get task info: dates
	 * 
	 * @return	List of dates
	 */
	public Set<UIDates> getTaskDates(UITask uitask)
	{
		log.debug("Getting task dates with " + uitask.getName());
		if(currentTask == null) 
		{
			for(Task t : allTasks)
			{
				if(t.getName().equals(uitask.getName()))
				{
					return t.getTaskDates();
				}
			}
		}
		else
		{
			return currentTask.getTaskDates();
		}
		return null;		
	}
	
	/**
	 * Get task info: resources
	 * 
	 * @return	List of resources
	 */
	public Set<UIResource> getTaskResources(UITask uitask)
	{
		log.debug("Getting task resources " + uitask.getName());
		if(currentTask == null)
		{
			for(Task t : allTasks)
			{
				if(t.getName().equals(uitask.getName()))
				{
					log.debug("Got " + t.getTaskResources().size());
					return t.getTaskResources();
				}
			}
		}
		else
		{
			return currentTask.getAllResources(this.oid);
		}		
		return null;		
	}
	
	/**
	 * Get task info: users
	 * 
	 * @return	List of users
	 */
	public Set<User> getTaskUsers(UITask uitask)
	{
		log.debug("Getting task users " + uitask.getName());
		if(currentTask == null)
		{
			for(Task t : allTasks)
			{
				if(t.getName().equals(uitask.getName()))
				{
					log.debug("Return size " + t.getTaskUsers().size());
					return t.getTaskUsers();
				}
			}
		}
		else
		{
			log.debug("Size of current task is " + currentTask.getTaskUsers().size());
			return currentTask.getTaskUsers();
		}
		log.debug("Nothing to return");
		return null;		
	}
	
	public void deleteTask(UITask uitask) {
		log.debug("Daleting task " + uitask.getName());
		this.mapper.deleteTaskById(currentTask);
		this.userTasks.remove(currentTask);
		this.allTasks.remove(currentTask);
		this.currentTask = null;
		for (Task t : allTasks) {
			if (t.getName().equals(uitask.getName())) {
				log.debug("Not NULL");
				return;
			}
		}
		log.debug("IS NULL");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		log.debug("HashCode is " + result);
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
	/*
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", login=" + login + ", password="
				+ password + "]";
	}*/
	
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

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}
	
	
}
