package ORM;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import logic.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;

public class TaskMapper extends AbstractMapper{
	public Set<Task> getTaskByDate(GregorianCalendar date)
	{
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String strDate=sdf.format(date.getTime());
		try{
			String query=String.format("from Tasks task where task.fromDate > to_date(%s) and toDate<to_date(%s)+1", strDate, strDate);
			Set<Task> tasks = new HashSet (this.readObject(query));	
			return tasks;
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public void deleteTaskById(Task task)
	{
		try{
			this.deleteObject(task);
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	/*public Set<Resource> getResourcesByTask(Task task)
	{
		try{
			String query=String.format("select r.* from Task t, Resources r, Assignments a, Resources_Assignments r_a " +
					"where t.IdTask=a.IdTask and a.IdAssignment=r_a.IdAssignment and r.IdResource=r_a.IdResource and t.IdTask=%d", task.getOid());
			Set<Resource> resources = new HashSet (this.readObject(query));	
			return resources;
		}
		catch(HibernateException he){
			throw he;
		}
	}*/
	
	public void getResourcesByDate(Set <Dates> dates)
	{
		//TODO: get all possible resources by date
		try{
			for (Dates d: dates)
			{	
				String query=String.format("select * from Resources where Resources.IdResource not in " +
												"(select r.IdResource " +
												"from Resources r, Assignments a, Resources_Assignments r_a " +
												"where a.IdAssignment=r_a.IdAssignment and r.IdResource=r_a.IdResource and " +
												"(%t between a.StartDate and a.FinishDate or %t between a.StartDate and a.FinishDate))",
												d.getStartDate(), d.getFinishDate());
				Set<Resource> resources = new HashSet (this.readObject(query));
				d.setResources(resources);
			}
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public void getDatesByTask(Task task)
	{
		try{
			String query=String.format("select a.* from Task t, Assignments a " +
					"where t.IdTask=a.IdTask and a.IdAssignment=r_a.IdAssignment and t.IdTask=%d", task.getOid());
			Set<Dates> dates = new HashSet (this.readObject(query));	
			task.setDates(dates);
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public Set<Group> getGroups()
	{
		try{
			String query=String.format("from Groups");
			Set<Group> groups = new HashSet (this.readObject(query));	
			return groups;
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public void getUsersByGroup(Group group)
	{
		try{
			String query=String.format("select u.* from Users u, Groups g " +
					"where g.IdGroup=u.IdGroupand g.IdGroup=%d", group.getOid());
			Set<User> users = new HashSet (this.readObject(query));	
			group.setUsers(users);
		}
		catch(HibernateException he){
			throw he;
		}
	}
}
