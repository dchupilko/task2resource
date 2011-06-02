package logic;

import java.util.HashSet;
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
	
	public void createUser(UIRequest uirequest) {
		Request request = new Request(uirequest);
		requestMapper.setRequest(request);
	}
	
	public void deleteUsers(Set<UIUser> uiusers) {
		//Set<Group> updatedGroups = new HashSet <Group> ();
		for (Group g : groups) {
			for (User u : g.users) {
				for (UIUser ui : uiusers) {
					if (u.equals(ui)) {
						//TODO: delete cascade
						//TODO: do this in one transaction
						userMapper.deleteUserById(u);
					}
				}
			}
		}
	}
		
	public void createResource(UIResource uiresource) {
		Resource res = new Resource(uiresource);
		resourceMapper.setResource(res);
	}
	
	public void deleteResources(Set<UIResource> uiresources) {
		for (Resource r : resources) {
			for (UIResource uir : uiresources) {
				if (r.equals(uir)) {
					resourceMapper.deleteResourceById(r);
				}
			}
		}
	}
	
	public Set<UIRequest> getAllRequests() {
		Set<UIRequest> uirequests = new HashSet<UIRequest>();
		requests = requestMapper.getAllRequests();
		
		for (Request r : requests) {
			uirequests.add(r.getUIRequest());
		}
		
		return uirequests;
	}
			
	public void acceptRequests(Set<UIRequest> uirequests){
		groups = userMapper.getGroups();
		for (Group g : groups) {
			userMapper.getUsersByGroup(g);
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
    		if (g.equals(uigroup)) {
    			userMapper.getUsersByGroup(g);
    			for (User u : g.users) {
    				uiUsers.add(u.getUIUser());
    			}
    		}
    	}
    	return uiUsers;
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
	    			if (u.equals(ui)) {
	    				users.add(u);
	    			}
    			}
    		}
    	}
    	currentUser.assignUsers(users);
    }
    
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
	

	public boolean Authorize (String login, String password)
	{
		//TODO: make authorization
		return true;
	}
	
	public void acceptTask() {
		currentUser.acceptTask();
	}
	
	public Set<UIResource> createTask(UITask task) {
		return currentUser.createTask(task);
	}
	
	public Set<UIDates> chooseResources(Set<UIResource> resources) {
		return currentUser.chooseResources(resources);
	}
	
	public Set<UIResource> resolveConflict(UIDates date) {
    	return currentUser.resolveConflict(date);
    }
	
	public void chooseResourcesForDate(Set<UIResource> resources, UIDates date) {
    	currentUser.chooseResourcesForDate(resources, date);
    }

	public void modifyDates(UITask uitask) {
		currentUser.modifyDates(uitask);
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
	
	public Set<UITask> getAllTasks()
	{
		return currentUser.getAllTasks();
	}
	
	public boolean modifyTask (UITask uitask)
	{
		return currentUser.modifyTask(uitask);
	}
}
