package logic;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import uiclasses.*;

public class Group {
	
	private static final Logger log = Logger.getLogger(Group.class);
	
	protected int oid;
	protected int version;
	
	protected String name;
    
	protected Set<User> users = new HashSet<User>();
    
    public Group() {}

    public Group(UIGroup uigroup) {
    	this.name = uigroup.getName();
    }
    
    
    // M E T H O D S
    
    /**
	 * Group info to pass to UI
	 * 
	 * @return	UIGroup class instance
	 */
    public UIGroup getUIGroup() {
    	return new UIGroup(this.name);
    }

    /**
     * Add user to group
     * 
     * @param user	User
     */
    public void addUser(User user) {
    	log.debug("Was added user: " + user.getOid() + " " + user.getLogin());
    	users.add(user);
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		log.debug("Hash code " + result);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Group other = (Group) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
