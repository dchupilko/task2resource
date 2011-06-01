package ORM;

import java.util.HashSet;
import java.util.Set;

import logic.*;

import org.hibernate.HibernateException;

public class RequestMapper extends AbstractMapper{
	
	public Set<Request> getAllRequests(){
		try{
			String query = "from Requests";
			Set<Request> requests = new HashSet(this.readObject(query)); 
			return requests;
		} catch(HibernateException he){
			throw he;
		}
	}
	
	public void setRequest(Request request){
		try{
			this.insertObject(request);
		}catch(HibernateException he){
			throw he;
		}
	}
	
	public void deleteRequests(Set<Request> requests){
		try{		
			//TODO: try it
			this.deleteObjects(new HashSet<Object>(requests));
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public Group getGroupByJob(String jobName)
	{
		try{
			String query=String.format("select g.* from Groups g, Jobs j " +
					"where j.Name=%s and j.IdGroup=g.IdGroup", jobName);
			return (Group)(this.readObject(query)).get(0);	
		}
		catch(HibernateException he){
			throw he;
		}
	}
}
