package logic;

import java.util.HashSet;
import java.util.Set;

import uiclasses.*;
import ORM.*;

public class Main {
	protected Set<Request> requests = new HashSet<Request>();
    protected Set<Group> groups = new HashSet<Group>();
    protected Set<Resource> resources = new HashSet<Resource>();
    
	protected RequestMapper requestMapper = new RequestMapper();
	protected UserMapper userMapper = new UserMapper();
	protected ResourceMapper resMapper = new ResourceMapper();
	
	User currentUser = new User();
	
	public void createUser(UIRequest uirequest) {
		Request request = new Request(uirequest);
		requestMapper.setRequest(request);
	}
	
	public void deleteUsers(Set<UIUser> uiusers) {
		for (Group g : groups) {
			for (User u : g.users) {
				for (UIUser ui : uiusers) {
					if (u.equals(ui)) {
						//TODO: delete cascade
						userMapper.deleteUserById(u);
					}
				}
			}
		}
	}
		
	public void createResource(UIResource uiresource) {
		Resource res = new Resource(uiresource);
		resMapper.setResource(res);
	}
	
	/*public void deleteResources(Set<UIResource> uiresources) {
		for (Resource r : resources) {
			for (UIResource uir : uiresources) {
				if (r.equals(uir)) {
					resMapper.deleteResourceById(r);
				}
			}
		}
	}*/
	
	public Set<UIRequest> getAllRequests() {
		Set<UIRequest> uirequests = new HashSet<UIRequest>();
		requests = requestMapper.getAllRequests();
		
		for (Request r : requests) {
			uirequests.add(r.getUIRequest());
		}
		
		return uirequests;
	}
			
	public void acceptRequests(Set<UIRequest> uirequests){
		while(uirequests.iterator().hasNext()) {
			UIRequest uiRequest = uirequests.iterator().next();
			//TODO: Make some user checking
			if(true){
				//Group group = userMapper.getGroupByJob(uiRequest.getJob());
				//groups.add(group);
			}
		}
		userMapper.setGroups(groups);
	}

	public void denyRequests(Set<UIRequest> uirequests){
		Set<Request> deniedRequests = new HashSet<Request>();
		for (Request r : requests) {
			for (UIRequest uir : uirequests) {
				if (r.equals(uir)) {
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
    
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
