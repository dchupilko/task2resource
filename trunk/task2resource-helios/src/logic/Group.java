package logic;

import java.util.HashSet;
import java.util.Set;

import uiclasses.*;

public class Group {
	protected int oid;
	protected int version;
	
	protected String name;
    
	protected Set<User> users = new HashSet<User>();
    
    public Group() {}

    
    // M E T H O D S
    
    public UIGroup getUIGroup() {
    	return new UIGroup(this.name);
    }
    
    public void addUser(UIRequest uirequest) {
    	users.add(new User(uirequest));
    	
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//if (!(obj instanceof Group))
			//return false;
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
