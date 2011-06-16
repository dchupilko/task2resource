package ORM;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logic.*;

import org.hibernate.HibernateException;

public class UserMapper extends AbstractMapper{
	public void setUser(User usr){
		try{
			this.insertObject(usr);
		}catch(HibernateException he){
			throw he;
		}
	}
	
	public void updateUser(User usr){
		try{
			this.updateObject(usr);
		}catch(HibernateException he){
			throw he;
		}
	}
	
	public void deleteRequest (Request request){
		try{
			this.deleteObject(request);
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
			List<Object[]> tempList = this.readObject(query);
		    Set<Group> groups = new HashSet<Group>();
		    for(Object[] o: tempList)
		    {
		    	Group g = new Group();
		    	g.setOid((new Integer(o[0].toString()).intValue()));
		    	g.setVersion((new Integer(o[1].toString()).intValue()));
		    	g.setName(o[2].toString());
		    	g.setACL((new Integer(o[3].toString()).intValue()));
	    		groups.add(g);
		    }	
			return groups;
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public Group getGroupByJob(String job)
	{
		try{
			String query=String.format("Select g.* from Groups g, Jobs j where j.IdGroup=g.IdGroup and j.Name='%s'", job);
			List<Object[]> tempList = this.readObject(query);
			Group g = new Group();
		    for(Object[] o: tempList)
		    {
		    	g.setOid((new Integer(o[0].toString()).intValue()));
		    	g.setVersion((new Integer(o[1].toString()).intValue()));
		    	g.setName(o[2].toString());
		    }
		    return g;
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
	
	public void getUsersByGroup(Group group, User currentUser)
	{
		try{
			String query=String.format("select u.* from Users u, Groups g " +
					"where g.IdGroup=u.IdGroup and g.IdGroup=%d and u.IdUser<>%d", group.getOid(), currentUser.getOid());
			List<Object[]> tempList = this.readObject(query);
		    Set<User> users = new HashSet<User>();
		    for(Object[] o: tempList)
		    {
		    	User u = new User();
		    	u.setOid((new Integer(o[0].toString()).intValue()));
		    	u.setVersion((new Integer(o[1].toString()).intValue()));
		    	u.setFirstName(o[2].toString());
		    	u.setLastName(o[3].toString());
		    	u.setEmail(o[4].toString());
		    	u.setLogin(o[5].toString());
		    	u.setPassword(o[6].toString());
	    		users.add(u);
		    }	
			group.setUsers(users);
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public User getUser(String login, String password)
	{
		try{
			String query=String.format("select * from Users " +
					"where Login='%s' and Password='%s'", login, password);
			List<Object[]> tempList = this.readObject(query);
		    User u = new User();
		    for(Object[] o: tempList)
		    {
		    	u.setOid((new Integer(o[0].toString()).intValue()));
		    	u.setVersion((new Integer(o[1].toString()).intValue()));
		    	u.setFirstName(o[2].toString());
		    	u.setLastName(o[3].toString());
		    	u.setEmail(o[4].toString());
		    	u.setLogin(o[5].toString());
		    	u.setPassword(o[6].toString());
		    }	
		    return u;
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public boolean checkUser(User user)
	{
		try{
			String query=String.format("select g.name from Users u, Groups g " +
					"where u.IdUser=%d and u.IdGroup=g.IdGroup", user.getOid());
			List<Object[]> tempList = this.readObject(query);
			if(tempList.size()==0)
				return false;
			Object o = tempList.get(0);
		    if(o.toString().equals("Admin"))
		       	return true;
		    else 	
		    	return false;
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public boolean checkLogin(String login)
	{
		try{
			String query=String.format("select * from Users where login = '%s'", login);
			List<Object[]> tempList = this.readObject(query);
			if(tempList.size()==0)
				return true;
		    else 	
		    	return false;
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public boolean checkEmail(String email)
	{
		try{
			String query=String.format("select * from Users where email = '%s'", email);
			List<Object[]> tempList = this.readObject(query);
			if(tempList.size()==0)
				return true;
		    else 	
		    	return false;
		}
		catch(HibernateException he){
			throw he;
		}
	}
}
