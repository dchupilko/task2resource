package ORM;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import logic.Task;

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
	
	
}
