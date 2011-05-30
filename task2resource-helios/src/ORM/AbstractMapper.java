package ORM;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AbstractMapper {
	public void insertObject (Object obj)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
        }
        catch (HibernateException he) {
            if (tx!=null) tx.rollback();
            throw he;
        }
        finally {
            session.close();
        }
	}
	
	public void updateObject (Object obj)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }
        catch (HibernateException he) {
            if (tx!=null) tx.rollback();
            throw he;
        }
        finally {
            session.close();
        }
	}
	
	public void deleteObject (Object obj)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }
        catch (HibernateException he) {
            if (tx!=null) tx.rollback();
            throw he;
        }
        finally {
            session.close();
        }
	}
	
	public List<Object> readObject (String statement)
	{
		Session session = null;
	     List<Object> objects = new ArrayList<Object>();
	     try 
	     {
	       session = HibernateUtil.getSessionFactory().openSession();
	       Query query=session.createQuery(statement);
	       objects = (List<Object>) query.list();
	     } 
	     catch (HibernateException he) 
	     {
	            throw he;
	     } 
	     finally 
	     { 
	         session.close();
	     }	   
	     return objects;
	}
}


