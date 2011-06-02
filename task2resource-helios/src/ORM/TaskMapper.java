package ORM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.List;

import logic.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.cache.config.parsing.ParsedAttributes;

import uiclasses.UIResource;

public class TaskMapper extends AbstractMapper{
	public Set<Task> getTaskByDate(GregorianCalendar date)
	{
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String strDate=sdf.format(date.getTime());
		try{
			String query=String.format("Select * from Tasks task where task.fromDate > to_date(%s) and toDate<to_date(%s)+1", strDate, strDate);
			List<Object[]> tempList = this.readObject(query);
		    Set<Task> tasks = new HashSet<Task>();
		    for(Object[] o: tempList)
		    {
		    	Task t = new Task();
		    	t.setOid((new Integer(o[0].toString()).intValue()));
		    	t.setVersion((new Integer(o[1].toString()).intValue()));
		    	t.setName(o[2].toString());
		    	t.setCapacity((new Integer(o[3].toString()).intValue()));
		    	Date d = new Date(o[4].toString());
		    	GregorianCalendar cal = new GregorianCalendar();
		    	cal.setTime(d);
		    	t.setFromDate(cal);
		    	d = new Date(o[5].toString());
		    	cal.setTime(d);
		    	t.setToDate(cal);
	    		tasks.add(t);
		    }
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
		//TODO: perhaps it's better to do this in the only transaction
		try{
			for (Dates d: dates)
			{	
				String query=String.format("Select * from Resources where Resources.IdResource not in " +
												"(select r.IdResource " +
												"from Resources r, Assignments a, Resources_Assignments r_a " +
												"where a.IdAssignment=r_a.IdAssignment and r.IdResource=r_a.IdResource and " +
												"(to_date(%tD) between a.StartDate and a.FinishDate or to_date(%tD) between a.StartDate and a.FinishDate))",
												d.getStartDate(), d.getFinishDate());
			    List<Object[]> tempList = this.readObject(query);
			    Set<Resource> resources = new HashSet<Resource>();
			    for(Object[] o: tempList)
			    {
			    	Integer oid = new Integer(o[0].toString());
			    	Integer version = new Integer(o[1].toString());
			    	String name = o[2].toString();
			    	Integer capacity = new Integer(o[3].toString());
			    	Resource r = new Resource(oid.intValue(), version.intValue(), name, capacity.intValue());
			    	resources.add(r);
			    }
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
			List<Object[]> tempList = this.readObject(query);
		    Set<Dates> dates = new HashSet<Dates>();
		    for(Object[] o: tempList)
		    {
		    	Dates date = new Dates();
		    	date.setOid((new Integer(o[0].toString()).intValue()));
		    	date.setVersion((new Integer(o[1].toString()).intValue()));
		    	Date d = new Date(o[2].toString());
		    	GregorianCalendar cal = new GregorianCalendar();
		    	cal.setTime(d);
		    	date.setStartDate(cal);
		    	d = new Date(o[3].toString());
		    	cal.setTime(d);
		    	date.setFinishDate(cal);
	    		dates.add(date);
		    }
			task.setDates(dates);
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public Set<Task> getAllTasks()
	{
		try {
			String query = "select t.* from Tasks t, Assignments a where a.startDate>=sysdate " +
					"and t.IdTask=a.IdTask"; 
			List<Object[]> tempList = this.readObject(query);
		    Set<Task> tasks = new HashSet<Task>();
		    for(Object[] o: tempList)
		    {
		    	Task t = new Task();
		    	t.setOid((new Integer(o[0].toString()).intValue()));
		    	t.setVersion((new Integer(o[1].toString()).intValue()));
		    	t.setName(o[2].toString());
		    	t.setCapacity((new Integer(o[3].toString()).intValue()));
		    	Date d = new Date(o[4].toString());
		    	GregorianCalendar cal = new GregorianCalendar();
		    	cal.setTime(d);
		    	t.setFromDate(cal);
		    	d = new Date(o[5].toString());
		    	cal.setTime(d);
		    	t.setToDate(cal);
	    		tasks.add(t);
		    }
			return tasks;
		} catch(HibernateException he){
			throw he;
		}
	}
	
	public Set<Task> getAllTasksForDates(Dates date)
	{
		//TODO: perhaps we'll need to extend dates' bounds
		try {
			String query = String.format("select t.* from Tasks t, Assignments a " +
					"where a.startDate>=to_date(%tD) and a.finishDate<=to_date(%tD) and t.IdTask=a.IdTask",
					date.getStartDate(), date.getFinishDate());
			List<Object[]> tempList = this.readObject(query);
		    Set<Task> tasks = new HashSet<Task>();
		    for(Object[] o: tempList)
		    {
		    	Task t = new Task();
		    	t.setOid((new Integer(o[0].toString()).intValue()));
		    	t.setVersion((new Integer(o[1].toString()).intValue()));
		    	t.setName(o[2].toString());
		    	t.setCapacity((new Integer(o[3].toString()).intValue()));
		    	Date d = new Date(o[4].toString());
		    	GregorianCalendar cal = new GregorianCalendar();
		    	cal.setTime(d);
		    	t.setFromDate(cal);
		    	d = new Date(o[5].toString());
		    	cal.setTime(d);
		    	t.setToDate(cal);
	    		tasks.add(t);
		    }
			return tasks;
		} catch(HibernateException he){
			throw he;
		}
	}
	
	public void getAllTasksById(User user)
	{
		try {
			String query = String.format("select t.* from Tasks t" +
					"wheret.IdUser=%d", user.getOid());
			 List<Object[]> tempList = this.readObject(query);
			    Set<Task> tasks = new HashSet<Task>();
			    for(Object[] o: tempList)
			    {
			    	Task t = new Task();
			    	t.setOid((new Integer(o[0].toString()).intValue()));
			    	t.setVersion((new Integer(o[1].toString()).intValue()));
			    	t.setName(o[2].toString());
			    	t.setCapacity((new Integer(o[3].toString()).intValue()));
			    	Date date = new Date(o[4].toString());
			    	GregorianCalendar cal = new GregorianCalendar();
			    	cal.setTime(date);
			    	t.setFromDate(cal);
			    	date = new Date(o[5].toString());
			    	cal.setTime(date);
			    	t.setToDate(cal);
		    		tasks.add(t);
			    }
			user.setUserTasks(tasks);
		} catch(HibernateException he){
			throw he;
		}
	}
	
	public void getResourcesByTask(Task task)
	{
		Set<Dates> dates = task.getDates();
		try{
			for (Dates d: dates)
			{	
				String query=String.format("select r.* " +
												"from Resources r, Assignments a, Resources_Assignments r_a " +
												"where a.IdAssignment=r_a.IdAssignment and r.IdResource=r_a.IdResource " +
												"and a.idAssignment=%d", d.getOid());
				 List<Object[]> tempList = this.readObject(query);
				    Set<Resource> resources = new HashSet<Resource>();
				    for(Object[] o: tempList)
				    {
				    	Integer oid = new Integer(o[0].toString());
				    	Integer version = new Integer(o[1].toString());
				    	String name = o[2].toString();
				    	Integer capacity = new Integer(o[3].toString());
				    	Resource r = new Resource(oid.intValue(), version.intValue(), name, capacity.intValue());
				    	resources.add(r);
				    }
				d.setResources(resources);
			}
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public void getUsersByTask(Task task)
	{
		try{
			String query=String.format("select u.* " +
											"from Tasks t, Users u, Participations p " +
											"where t.IdTask=p.IdTask and u.IdUser=p.IdUser " +
											"and t.idTask=%d", task.getOid());
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
			task.setParticipants(users);
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public void setTask(Task task){
		//TODO: check dependencies while saving task
		try{
			this.insertObject(task);
		}catch(HibernateException he){
			throw he;
		}
	}
}
