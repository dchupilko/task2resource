package ORM;

import java.util.HashSet;
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
			/*
			 * Supposed "select * from resources;"
			 */
			String query = "from Resources";
			Set<Resource> resources = new HashSet(this.readObject(query)); 
			return resources;
		} catch(HibernateException he){
			throw he;
		}
	}
	
	

}
