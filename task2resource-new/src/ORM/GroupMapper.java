package ORM;

import org.hibernate.HibernateException;
import logic.*;

public class GroupMapper extends AbstractMapper{
	
	public void deleteGroupById(Group grp)
	{
		try{
			this.deleteObject(grp);
		}
		catch(HibernateException he){
			throw he;
		}
	}
	
	public void setGroup(Group grp){
		try{
			this.insertObject(grp);
		}catch(HibernateException he){
			throw he;
		}
	}
}
