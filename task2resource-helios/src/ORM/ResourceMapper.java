package ORM;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import logic.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;

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

}
