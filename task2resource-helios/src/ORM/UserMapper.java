package ORM;

import java.util.Date;
import java.util.GregorianCalendar;
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
	
	public void getUsersByGroup(Group group)
	{
		try{
			String query=String.format("select u.* from Users u, Groups g " +
					"where g.IdGroup=u.IdGroup and g.IdGroup=%d", group.getOid());
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
}
