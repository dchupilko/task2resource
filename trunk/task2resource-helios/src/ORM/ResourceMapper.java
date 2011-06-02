package ORM;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logic.Resource;
import org.hibernate.HibernateException;

public class ResourceMapper extends AbstractMapper{
	
	public void deleteResourceById(Resource res)
	{
		try{
			this.deleteObject(res);
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	
	public void setResource(Resource res){
		try{
			this.insertObject(res);
		}catch(HibernateException he){
			throw he;
		}
	}

	public Set<Resource> getAllResources(){
		try{
			String query = "Select * from Resources";
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
			return resources;
		} catch(HibernateException he){
			throw he;
		}
	}
	
	

}
