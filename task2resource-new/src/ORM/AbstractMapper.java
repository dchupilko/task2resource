package ORM;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.apache.log4j.Logger;

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
            (Logger.getLogger(this.getClass())).error("Error while inserting an object " +he);
            throw he;
        }
        finally {
            session.close();
        }
	}
	
	public void insertObjects (Set<Object> objects)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for(Object obj: objects)
            {            
            	session.save(obj);
            }
            tx.commit();
        }
        catch (HibernateException he) {
            if (tx!=null) tx.rollback();
            (Logger.getLogger(this.getClass())).error("Error while inserting collection of objects " + he);
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
            (Logger.getLogger(this.getClass())).error("Error while updating an object " + he);
            throw he;
        }
        finally {
            session.close();
        }
	}
	

	
	public void updateObjects (Set<Object> objects)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for(Object obj: objects)
            {
            	session.saveOrUpdate(obj);
            }            
            tx.commit();
        }
        catch (HibernateException he) {
            if (tx!=null) tx.rollback();
            (Logger.getLogger(this.getClass())).error("Error while updating collection of objects " + he);
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
            (Logger.getLogger(this.getClass())).error("Error while deleting an object " + he);
            throw he;
        }
        finally {
            session.close();
        }
	}
	
	public void deleteObjects (Set <Object> objects)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for(Object obj: objects)
            {
            	session.delete(obj);
            }
            tx.commit();
        }
        catch (HibernateException he) {
            if (tx!=null) tx.rollback();
            (Logger.getLogger(this.getClass())).error("Error while deleting collection of objects " + he);
            throw he;
        }
        finally {
            session.close();
        }
	}
	
	public List<Object []> readObject (String statement)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
	    List<Object []> objects = new ArrayList<Object []>();
	    try 
	    {
	    	Query query=session.createSQLQuery(statement);
	    	objects = (List<Object []>)query.list();
	    } 
	    catch (HibernateException he) 
	    {
	    	(Logger.getLogger(this.getClass())).error("Error while reading an object" + he);
	    	throw he;
	    } 
	    finally 
	    { 
	    	session.close();
	    }	   
	    return objects;
	}
	
	/*
	public Object refreshObject (int OID, Class cls)
	{
		Object object = new Object();
		Session session = HibernateUtil.getSessionFactory().openSession();
	    try 
	    {
	    	object = session.get(cls, OID);
	    	
	    } 
	    catch (HibernateException he) 
	    {
	    	throw he;
	    } 
	    finally 
	    { 
	    	session.close();
	    }	   
	    return object;
	}
	*/
}


