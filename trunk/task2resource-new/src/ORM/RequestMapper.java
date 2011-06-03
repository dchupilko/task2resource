package ORM;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logic.*;

import org.hibernate.HibernateException;

public class RequestMapper extends AbstractMapper{
	
	public Set<Request> getAllRequests(){
		try{
			String query = "Select * from Requests";
			List<Object[]> tempList = this.readObject(query);
		    Set<Request> requests = new HashSet<Request>();
		    for(Object[] o: tempList)
		    {
		    	Request r = new Request();
		    	r.setOid((new Integer(o[0].toString()).intValue()));
		    	r.setVersion((new Integer(o[1].toString()).intValue()));
		    	r.setFirstName(o[2].toString());
		    	r.setLastName(o[3].toString());
		    	r.setLogin(o[4].toString());
		    	r.setPassword(o[5].toString());
		    	r.setEmail(o[6].toString());
		    	r.setJob(o[7].toString());
	    		requests.add(r);
		    }
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
	
	/*public Group getGroupByJob(String jobName)
	{
		try{
			String query=String.format("select g.* from Groups g, Jobs j " +
					"where j.Name=%s and j.IdGroup=g.IdGroup", jobName);
			return (Group)(this.readObject(query)).get(0);	
		}
		catch(HibernateException he){
			throw he;
		}
	}*/
}
