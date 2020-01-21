package jedzonko.utils;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DBManager
{
	public static void init()
	{
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.getTransaction().commit();
	}
	
	public static void insert(Object o)
	{
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
	}
	
	public static void delete(Object o)
	{
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
	}
	
	public static void update(Object o)
	{
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.update(o);
		session.getTransaction().commit();
	}
	
	//Selects all records of given entity
	public static <T> List<T> selectAll(String entityName)
	{
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<T> list = session.createQuery("from " + entityName).list();
		session.getTransaction().commit();
		return list;
	}
	
	//Selects all records of given entity where given column matches given parameter
	public static <T> List<T> selectAllWhere(String className, String colName, String param)
	{
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM " + className + " WHERE " + colName + " = :param");
		q.setParameter("param", param);
		List<T> list = q.list();
		session.getTransaction().commit();
		return list;
	}
	
	//Selects a record of given entity where given column matches given parameter and row number matches given index
	public static <T> T selectOneWhere(String className, String colName, String param, int index)
	{
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Query q = session.createQuery("FROM " + className + " WHERE " + colName + " = :param");
		q.setParameter("param", param);
		q.setFirstResult(index);
		q.setMaxResults(1);
		session.getTransaction().commit();
		
		if (q.list().size() > 0)
		{
			return (T) q.list().get(0);
		}
		return null;
	}
}
