package logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import uiclasses.*;
import ORM.*;

public class Main {
	
	private static final Logger log = Logger.getLogger(Main.class);
	
    protected Set<Group> groups = new HashSet<Group>();
	protected Set<Request> requests = new HashSet<Request>();
    protected Set<Resource> resources = new HashSet<Resource>();
    
	protected UserMapper userMapper = new UserMapper();
	protected RequestMapper requestMapper = new RequestMapper();
	protected ResourceMapper resourceMapper = new ResourceMapper();
	protected GroupMapper groupMapper = new GroupMapper();
	
	protected User currentUser = new User();
	
	
	// M E T H O D S
	
	/**
	 * Create new request for registration
	 */
	public void createUser(UIRequest uirequest) {
		log.debug("Creating user from uirequest" + uirequest.getLogin());
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
		log.debug("Deleting users");
		for (Group g : groups) {
			for (User u : g.users) {
				for (UIUser ui : uiusers) {
					if (u.getFirstName().equals(ui.getFirstName()) && u.getLastName().equals(ui.getLastName())) {
						//TODO: delete cascade
						//TODO: do this in one transaction
						userMapper.deleteUserById(u);
						log.debug("Deleting by user ID " + u.getOid() + " " + u.getLogin());
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
	public boolean Authorize (String login, String password) {
		log.debug("Trying to authorize");
		currentUser = userMapper.getUser(login, password);
		if(currentUser.getLogin()==null && currentUser.getPassword()==null)
		{
			log.debug("Autorization failed! Login was [" + currentUser.getLogin() + 
					"] " + "password was [" + currentUser.getPassword() + "]");
			return false;
		}
		return true;
	}
	
	public boolean isAdministrator()
	{
		return userMapper.checkUser(currentUser);
	}
	
	/**
	 * Create new group
	 * 
	 * @param uigroup	Group info
	 */
	public void createGroup(UIGroup uigrp) {
		log.debug("Creating group with name " + uigrp.getName());
		Group grp = new Group(uigrp);
		groupMapper.setGroup(grp);
	}
	

	/**
	 * Create new resource
	 * 
	 * @param uiresource	Resource info
	 */
	public void createResource(UIResource uiresource) {
		log.debug("Getting resource " + uiresource.getName());
		Resource res = new Resource(uiresource);
		resourceMapper.setResource(res);
	}
	
	public Set <UIResource> getAllResources(){
		log.debug("Getting all resources...");
		Set<UIResource> uiResources = new HashSet<UIResource>();
		resources.addAll(resourceMapper.getAllResources());
		for(Resource r: resources)
		{
			uiResources.add(r.getUIResource());
			log.debug("Resource was added: " + r.getUIResource().getName());
		}
		return uiResources;
	}
	
	/**
	 * Delete several resources
	 * 
	 * @param uiresources	List of resources
	 */
	public void deleteResources(Set<UIResource> uiresources) {
		log.debug("Deleting resources");
		Set<Resource> delResources = new HashSet<Resource>();
		for (Resource r : resources) {
			for (UIResource uir : uiresources) {
				if (r.getName().equals(uir.getName())) {
					delResources.add(r);
					log.debug("Resourse " + r.getOid() + " " + r.getName() +
							"is going to be deleted");
				}
			}
		}
		resourceMapper.deleteResources(delResources);
		log.debug("All of them were deleted");
	}
	
	/**
	 * Get list of all requests for registration
	 * 
	 * @return	List of requests
	 */
	public Set<UIRequest> getAllRequests() {
		log.debug("Getting all requests");
		Set<UIRequest> uirequests = new HashSet<UIRequest>();
		requests = requestMapper.getAllRequests();
		
		for (Request r : requests) {
			uirequests.add(r.getUIRequest());
			log.debug("Request: " + r.getUIRequest().getLogin());
		}
		
		return uirequests;
	}
	
	/**
	 * Accept several registration requests (add users)
	 * 
	 * @param uirequests	List of requests
	 */
	public void acceptRequests(Set<UIRequest> uirequests){
		log.debug("Accepting requests");
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
						log.debug("Trying to add user" + user.getLogin() + " " +
								g.getName());
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
		log.debug("Finished accepting requests");
	}

	/**
	 * Dismiss several registration requests (delete requests)
	 * 
	 * @param uirequests	List of requests
	 */
	public void denyRequests(Set<UIRequest> uirequests){
		log.debug("Deny requests");
		Set<Request> deniedRequests = new HashSet<Request>();
		for (Request r : requests) {
			for (UIRequest uir : uirequests) {
				if (r.getLogin().equals(uir.getLogin())) {
					deniedRequests.add(r);
					log.debug("Going to deny: " + r.getLogin());
				}
			}
		}
		requestMapper.deleteRequests(deniedRequests);
		log.debug("These requests have been deleted");
	}
	
    /**
     * Get all available groups
     * 
     * @return	List of groups
     */
    public Set<UIGroup> getAllGroups () {
    	log.debug("Getting all groups");
    	groups = userMapper.getGroups();
    	Set<UIGroup> uiGroups = new HashSet<UIGroup>();
    	for (Group g : groups) {
    		uiGroups.add(g.getUIGroup());
    		log.debug(g.getUIGroup().getName());
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
    	log.debug("Getting users from group " + uigroup.getName());
    	Set<UIUser> uiUsers = new HashSet<UIUser>();
    	for (Group g : groups) {
    		if (g.getName().equals(uigroup.getName())) {
    			userMapper.getUsersByGroup(g, currentUser);
    			for (User u : g.users) {
    				uiUsers.add(u.getUIUser());
    				log.debug(u.getUIUser().getFirstName() + " " + u.getUIUser().getLastName());
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
		log.debug("Creating task" + task.getName());
		return currentUser.createTask(task);
	}
	
    /**
     * Assign selected resources to dates.
     * 
     * @param resources	List of selected resources
     * @return			Dates of conflicts
     */
	public Set<UIDates> chooseResources(Set<UIResource> resources) {
		log.debug("Choosing resourses from set");
		return currentUser.chooseResources(resources);
	}
	
    /**
     * Find replacement for a conflict
     * 
     * @param date	Conflict date
     * @return		List of available resources
     */
	public Set<UIResource> resolveConflict(UIDates date) {
		log.debug("Resolving conflict with date " + date.getStartDate().getTime() +
				" " + date.getFinishDate().getTime());
    	return currentUser.resolveConflict(date);
    }
	
    /**
     * Assign selected resources to specified date
     * 
     * @param resources	List of selected resources
     * @param date		Date of assignment
     */
	public void chooseResourcesForDate(Set<UIResource> resources, UIDates date) {
		log.debug("Choosing set of resources for date " + date.getStartDate().getTime());
    	currentUser.chooseResourcesForDate(resources, date);
    }
	    
    /**
     * Assign users to current task
     * 
     * @param uiusers	List of selected users
     */
    public void assignUsers(Set<UIUser> uiusers) {
    	log.debug("Assigning users");
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
		log.debug("Accepting task for " + currentUser.getLogin());
		currentUser.acceptTask();
	}
		
	/**
	 * Get list of all tasks
	 * 
	 * @return	List of all tasks
	 */
	public List<UITask> getAllTasks() 
	{
		log.debug("Getting all tasks");
		return currentUser.getAllTasks();
	}
	
	public List<UITask> getAllUserTasks() 
	{
		log.debug("Getting all user tasks");
		return currentUser.getAllUserTasks();
	}
	/**
	 * Get list of tasks for specified dates
	 * 
	 * @param uidate	Dates
	 * @return			List of tasks
	 */
	public Set<UITask> getAllTasksForDates(UIDates uidate) {
		log.debug("Getting all tasks for date " + uidate.getStartDate().getTime() +
				" - " + uidate.getFinishDate().getTime());
		return currentUser.getAllTasksForDates(uidate);
	}
	
	/**
	 * Initialize modifying a task process
	 * 
	 * @return	 true in case current user is the owner of selected task
	 */
	public boolean modifyTask (UITask uitask)
	{
		log.debug("Trying to modify task");
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
		log.debug("Trying to modify resources");
		return currentUser.modifyResources(addedResources, removedResources);
	}
	
	/**
	 * Edit task participants
	 * 
	 * @param addedUsers	List of added users
	 * @param removedUsers	List of removed users
	 */
	public void modifyUsers(Set<UIUser> uiAddedUsers, Set<UIUser> uiRemovedUsers) {
		log.debug("Trying to modify users");
		groups = userMapper.getGroups();
		Set<User> addedUsers = new HashSet<User>();
		Set<User> removedUsers = new HashSet<User>();		
    	for (Group g : groups) {
    		userMapper.getUsersByGroup(g, currentUser);
    		for (User u : g.users) {
    			for (UIUser ui : uiAddedUsers) {
	    			if (u.getFirstName().equals(ui.getFirstName()) && u.getLastName().equals(ui.getLastName())) {
	    				addedUsers.add(u);
	    				log.debug("Added user " + u.getLogin());
	    			}
    			}
    			for (UIUser ui : uiRemovedUsers) {
    				if (u.getFirstName().equals(ui.getFirstName()) && u.getLastName().equals(ui.getLastName())) {
	    				removedUsers.add(u);
	    				log.debug("Removed user " + u.getLogin());
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
		log.debug("Modifying dates for task " + uitask.getName() + " " + uitask.getDescription());
		return currentUser.modifyDates(uitask);
	}
	
	/**
	 * Edit task name and capacity
	 * 
	 * @param uitask	Task info
	 */
	public void modifyInfo(UITask uitask) {
		log.debug("Trying to modify info" + uitask.getDescription());
		currentUser.modifyInfo(uitask);
	}
	
	/**
	 * Get task info: dates
	 * 
	 * @return	List of dates
	 */
	public Set<UIDates> getTaskDates(UITask uitask)
	{
		log.debug("Getting task dates");
		return currentUser.getTaskDates(uitask);
	}
	
	/**
	 * Get task info: resources
	 * 
	 * @return	List of resources
	 */
	public Set<UIResource> getTaskResources(UITask uitask)
	{
		log.debug("Getting task resources");
		return currentUser.getTaskResources(uitask);
	}
	
	/**
	 * Get task info: users
	 * 
	 * @return	List of users
	 */
	public Set<UIUser> getTaskUsers(UITask uitask)
	{
		log.debug("Getting task users");
		return currentUser.getTaskUsers(uitask);
	}
	
	public void deleteTask(UITask uitask) {
		log.debug("Deleting task " + uitask.getName());
		currentUser.deleteTask(uitask);
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
