package logic;

import java.util.HashSet;
import java.util.Set;

public class Group {
	protected int oid;
	protected String name;
    protected Set<User> users = new HashSet<User>();
    
    public Group() {}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
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
