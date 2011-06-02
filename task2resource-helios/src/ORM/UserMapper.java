package ORM;

import java.util.HashSet;
import java.util.Set;

import logic.Group;
import logic.User;

import org.hibernate.HibernateException;

public class UserMapper extends AbstractMapper{
	public void setUser(User usr){
		try{
			this.insertObject(usr);
		}catch(HibernateException he){
			throw he;
		}
	}
	
	public void deleteUserById (User usr){
		try{
			this.deleteObject(usr);
		}catch(HibernateException he){
			throw he;
		}
	}
	
	public Set<Group> getGroups()
	{
		try{
			String query=String.format("Select * from Groups");
			Set<Group> groups = new HashSet (this.readObject(query));	
			return groups;
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public void setGroups(Set<Group> groups) {
		try {
			for (Group g : groups) {
				this.updateObject(g);
			}
		}
		catch(HibernateException he) {
			throw he;
		}
	}
	
	public void getUsersByGroup(Group group)
	{
		try{
			String query=String.format("select u.* from Users u, Groups g " +
					"where g.IdGroup=u.IdGroup and g.IdGroup=%d", group.getOid());
			Set<User> users = new HashSet (this.readObject(query));	
			group.setUsers(users);
		}
		catch(HibernateException he){
			throw he;
		}
	}
}
