package ORM;

import java.util.HashSet;
import java.util.Set;

import logic.Request;

import org.hibernate.HibernateException;

public class RequestMapper extends AbstractMapper{
	
	public Set<Request> getAllRequests(){
		try{
			/*
			 * Supposed "select * from requests;"
			 */
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
	
	public void deleteRequest(Request request){
		try{
			this.deleteObject(request);
		}
		catch(HibernateException he){
			throw he;
		}
	}
}
