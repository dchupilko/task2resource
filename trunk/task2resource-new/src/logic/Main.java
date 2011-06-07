package logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uiclasses.*;
import ORM.*;

public class Main {
    protected Set<Group> groups = new HashSet<Group>();
	protected Set<Request> requests = new HashSet<Request>();
    protected Set<Resource> resources = new HashSet<Resource>();
    
	protected UserMapper userMapper = new UserMapper();
	protected RequestMapper requestMapper = new RequestMapper();
	protected ResourceMapper resourceMapper = new ResourceMapper();
	
	protected User currentUser = new User();
	
	
	// M E T H O D S
	
	/**
	 * Create new request for registration
	 */
	public void createUser(UIRequest uirequest) {
		Request request = new Request(uirequest);
		requestMapper.setRequest(request);
	}
	
	/**
	 * Delete several users
	 * 
	 * @param uiusers	List of users
	 */
	public void deleteUsers(Set<UIUser> uiusers) {
		//Set<Group> updatedGroups = new HashSet <Group> ();
		for (Group g : groups) {
			for (User u : g.users) {
				for (UIUser ui : uiusers) {
					if (u.getFirstName().equals(ui.getFirstName()) && u.getLastName().equals(ui.getLastName())) {
						//TODO: delete cascade
						//TODO: do this in one transaction
						userMapper.deleteUserById(u);
					}
				}
			}
		}
	}
	
	/**
	 * Check username and password
	 * 
	 * @return	true if access granted
	 */
	public boolean Authorize (String login, String password)
	{
		currentUser = userMapper.getUser(login, password);
		if(currentUser.getLogin()==null && currentUser.getPassword()==null)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Create new resource
	 * 
	 * @param uiresource	Resource info
	 */
	public void createResource(UIResource uiresource) {
		Resource res = new Resource(uiresource);
		resourceMapper.setResource(res);
	}
	
	public Set <UIResource> getAllResources()
	{
		Set<UIResource> uiResources = new HashSet<UIResource>();
		Set<Resource> allResources = resourceMapper.getAllResources();
		for(Resource r: allResources)
		{
			uiResources.add(r.getUIResource());
		}
		return uiResources;
	}
	
	/**
	 * Delete several resources
	 * 
	 * @param uiresources	List of resources
	 */
	public void deleteResources(Set<UIResource> uiresources) {
		for (Resource r : resources) {
			for (UIResource uir : uiresources) {
				if (r.equals(uir)) {
					resourceMapper.deleteResourceById(r);
				}
			}
		}
	}
	
	/**
	 * Get list of all requests for registration
	 * 
	 * @return	List of requests
	 */
	public Set<UIRequest> getAllRequests() {
		Set<UIRequest> uirequests = new HashSet<UIRequest>();
		requests = requestMapper.getAllRequests();
		
		for (Request r : requests) {
			uirequests.add(r.getUIRequest());
		}
		
		return uirequests;
	}
	
	/**
	 * Accept several registration requests (add users)
	 * 
	 * @param uirequests	List of requests
	 */
	public void acceptRequests(Set<UIRequest> uirequests){
		groups = userMapper.getGroups();
		for (Group g : groups) {
			userMapper.getUsersByGroup(g, currentUser);
		}
		Group group;
		for (UIRequest uirequest : uirequests) {
			//TODO: Make some user checking
			if(true){
				group = userMapper.getGroupByJob(uirequest.getJob());
				for (Group g : groups) {
					if (g.equals(group)) {
						User user = new User(uirequest);
						g.addUser(user);
						userMapper.setUser(user);
						for (Request r : requests) {
							if (r.getLogin().equals(uirequest.getLogin())) {
								userMapper.deleteRequest(r);
							}
						}
					}
				}
			}
		}
		userMapper.setGroups(groups);
	}

	/**
	 * Dismiss several registration requests (delete requests)
	 * 
	 * @param uirequests	List of requests
	 */
	public void denyRequests(Set<UIRequest> uirequests){
		Set<Request> deniedRequests = new HashSet<Request>();
		for (Request r : requests) {
			for (UIRequest uir : uirequests) {
				if (r.getLogin().equals(uir.getLogin())) {
					deniedRequests.add(r);
				}
			}
		}
		requestMapper.deleteRequests(deniedRequests);
	}
	
    /**
     * Get all available groups
     * 
     * @return	List of groups
     */
    public Set<UIGroup> getAllGroups () {
    	groups = userMapper.getGroups();
    	Set<UIGroup> uiGroups = new HashSet<UIGroup>();
    	for (Group g : groups) {
    		uiGroups.add(g.getUIGroup());
    	}
    	return uiGroups;
    }

    /**
     * Get all users from a specified group
     * 
     * @param uigroup	Group name
     * @return			List of users
     */
    public Set<UIUser> getAllUsersFromGroup(UIGroup uigroup) {
    	Set<UIUser> uiUsers = new HashSet<UIUser>();
    	for (Group g : groups) {
    		if (g.getName().equals(uigroup.getName())) {
    			userMapper.getUsersByGroup(g, currentUser);
    			for (User u : g.users) {
    				uiUsers.add(u.getUIUser());
    			}
    		}
    	}
    	return uiUsers;
    }
	
	/**
	 * Initialize creating new task process
	 * 
	 * @param task	Info about task
	 * @return		List of all resources
	 */
	public Set<UIResource> createTask(UITask task) {
		return currentUser.createTask(task);
	}
	
    /**
     * Assign selected resources to dates.
     * 
     * @param resources	List of selected resources
     * @return			Dates of conflicts
     */
	public Set<UIDates> chooseResources(Set<UIResource> resources) {
		return currentUser.chooseResources(resources);
	}
	
    /**
     * Find replacement for a conflict
     * 
     * @param date	Conflict date
     * @return		List of available resources
     */
	public Set<UIResource> resolveConflict(UIDates date) {
    	return currentUser.resolveConflict(date);
    }
	
    /**
     * Assign selected resources to specified date
     * 
     * @param resources	List of selected resources
     * @param date		Date of assignment
     */
	public void chooseResourcesForDate(Set<UIResource> resources, UIDates date) {
    	currentUser.chooseResourcesForDate(resources, date);
    }
	    
    /**
     * Assign users to current task
     * 
     * @param uiusers	List of selected users
     */
    public void assignUsers(Set<UIUser> uiusers) {
    	Set<User> users = new HashSet<User>();
    	for (Group g : groups) {
    		for (User u : g.users) {
    			for (UIUser ui : uiusers) {
	    			if (u.getFirstName().equals(ui.getFirstName())&&u.getLastName().equals(ui.getLastName())) {
	    				users.add(u);
	    			}
    			}
    		}
    	}
    	currentUser.assignUsers(users);
    }
    
    /**
     * Complete creating task
     */
	public void acceptTask() {
		currentUser.acceptTask();
	}
		
	/**
	 * Get list of all tasks
	 * 
	 * @return	List of all tasks
	 */
	public List<UITask> getAllTasks() 
	{
		return currentUser.getAllTasks();
	}
	
	/**
	 * Get list of tasks for specified dates
	 * 
	 * @param uidate	Dates
	 * @return			List of tasks
	 */
	public Set<UITask> getAllTasksForDates(UIDates uidate) {
		return currentUser.getAllTasksForDates(uidate);
	}
	
	/**
	 * Initialize modifying a task process
	 * 
	 * @return	 true in case current user is the owner of selected task
	 */
	public boolean modifyTask (UITask uitask)
	{
		return currentUser.modifyTask(uitask);
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
		return currentUser.modifyResources(addedResources, removedResources);
	}
	
	/**
	 * Edit task participants
	 * 
	 * @param addedUsers	List of added users
	 * @param removedUsers	List of removed users
	 */
	public void modifyUsers(Set<UIUser> uiAddedUsers, Set<UIUser> uiRemovedUsers) {
		Set<User> addedUsers = new HashSet<User>();
		Set<User> removedUsers = new HashSet<User>();
    	for (Group g : groups) {
    		for (User u : g.users) {
    			for (UIUser ui : uiAddedUsers) {
	    			if (u.equals(ui)) {
	    				addedUsers.add(u);
	    			}
    			}
    			for (UIUser ui : uiRemovedUsers) {
	    			if (u.equals(ui)) {
	    				removedUsers.add(u);
	    			}
    			}
    		}
    	}
    	currentUser.modifyUsers(addedUsers, removedUsers);
	}
	
	/**
	 * Edit task dates, period or length in minutes
	 * Deletes everything except name and capacity and starts use case "create task"
	 * 
	 * @param uitask	Task info
	 */
	public Set<UIResource> modifyDates(UITask uitask)
	{
		return currentUser.modifyDates(uitask);
	}
	
	/**
	 * Edit task name and capacity
	 * 
	 * @param uitask	Task info
	 */
	public void modifyInfo(UITask uitask) {
		currentUser.modifyInfo(uitask);
	}
	
	/**
	 * Get task info: dates
	 * 
	 * @return	List of dates
	 */
	public Set<UIDates> getTaskDates()
	{
		return currentUser.getTaskDates();
	}
	
	/**
	 * Get task info: resources
	 * 
	 * @return	List of resources
	 */
	public Set<UIResource> getTaskResources()
	{
		return currentUser.getTaskResources();
	}
	
	/**
	 * Get task info: users
	 * 
	 * @return	List of users
	 */
	public Set<UIUser> getTaskUsers()
	{
		return currentUser.getTaskUsers();
	}
	

    // A C C E S S O R S
    
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
}
