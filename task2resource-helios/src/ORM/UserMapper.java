package ORM;

import logic.User;

import org.hibernate.HibernateException;

public class UserMapper extends AbstractMapper{
	public void setUser(User usr){
		try{
			this.insertObject(usr);
		}catch(HibernateException he){
			throw he;
		}
	}
	
	
	public void deleteUserById (User usr){
		try{
			this.deleteObject(usr);
		}catch(HibernateException he){
			throw he;
		}
	}
	
}
