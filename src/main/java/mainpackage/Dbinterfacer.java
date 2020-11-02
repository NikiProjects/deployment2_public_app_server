package mainpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.Query;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
// import org.hibernate.SessionFactory;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;


import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import mainpackage.beans.User;


@Configuration
@EnableTransactionManagement
@ComponentScan({"mainpackage"})
@Repository
public class Dbinterfacer {
//JdbcTemplate jdbctemplate;
//LocalSessionFactoryBean sessionFactory;	


	 
	@Bean
	   public BasicDataSource restDataSource() {
	      BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/spring_database?useSSL=false");
	      dataSource.setUsername("root");
	      dataSource.setPassword("ILN19#");
	 
	      return dataSource;
	   }
	
	//helper function
	 Properties hibernateProperties() {
		   Properties hibernateProperties = new Properties();
		   hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL57Dialect");

	        hibernateProperties.setProperty("hibernate.show_sql", "true");
	        hibernateProperties.setProperty("hibernate.format_sql", "true");
	        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
		
	        return hibernateProperties;
	   
	}
	
		
	 	@Bean(name = "sessionFactory")
	   public LocalSessionFactoryBean sessionFactory() {
	      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	      sessionFactory.setDataSource(restDataSource());
	      sessionFactory.setPackagesToScan(
	        new String[] {"mainpackage.beans"});
	      sessionFactory.setHibernateProperties(hibernateProperties());
	      
	      return sessionFactory;
	   }
	 
	   
	 
	   @Bean
	    public HibernateTransactionManager getTransactionManager() {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	  

	
@Autowired
private SessionFactory sessionFactory;
//LocalSessionFactoryBean sessFactoryBean;









/*
public SessionFactoryImpl getSessionFactory() {
	return sessionFactory;
}


public void setSessionFactory(SessionFactoryImpl sessionFactory) {
	this.sessionFactory = sessionFactory;
}



/*public void initializeJdbcTemplate(JdbcTemplate jdbcTemplate){
	this.jdbcTemplate = jdbcTemplate;
}  */



public void insertOneRecord(User user) {    //used once in Controller class
	Transaction transaction = null;
	Session session = null;
	
	System.out.println("Inside insertOneRecord function");
	try{
		//session = HibernateUtil.getSession();
		
	
		session = sessionFactory.openSession();
		System.out.println("After opening new session");
		
		if(session == null){
			System.out.println("session is null");
		}
		else if(session != null){
			System.out.println("session is not null");
		}
	
		transaction = session.beginTransaction();
	
		session.save(user);
	
		transaction.commit();
	}
	catch(Exception e){
		if(transaction != null)
			transaction.rollback();
	e.printStackTrace();		
	}
	finally{
		session.close();
	
	}

}



public void update(User user){
	Transaction transaction = null;
	Session session = null;
	
	try
	{
	
	
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		if(session != null)
		{
			
			User userFromDb = (User)session.load(User.class, user.getId());
			userFromDb.setAge(user.getAge());
			userFromDb.setEmail(user.getEmail());
			userFromDb.setGender(user.getGender());
			userFromDb.setId(user.getId());
			userFromDb.setName(user.getName());
			userFromDb.setPassword(user.getPassword());
			userFromDb.setUsername(user.getUsername());
			
					
			session.flush();
			transaction.commit();
		}
		}
	catch(Exception e){
		if(transaction != null)
			transaction.rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
	}
}


public void delete(int id) {
	Transaction transaction = null;
	Session session = null;
	User user = null;
	try{
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	
		user = (User) session.load(User.class, new Integer(id)); // accessing record from db
		if(null != user){
			session.delete(user);
		}
	
		session.flush();
		transaction.commit();
	}
	catch(Exception e){
		if(transaction != null)
			transaction.rollback();
		e.printStackTrace();
	}
	finally{
		session.close();
		
	}
	
	System.out.println("Person deleted successfully, user details="+ user);
}



public User getUserById(int id) {
	Session session = null;
	User user = null;
	try{
		session = sessionFactory.openSession();		
		user = (User) session.load(User.class, new Integer(id));
		System.out.println("getUserById User retrieved successfully, User details="+user);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	return user;
}


public List<User> getUsers() {
	Session session = null;
	List<User> userList = new ArrayList<User>();
	try{
		session = sessionFactory.openSession();
		userList = session.createQuery("from User").list();
		for(User u : userList){
		System.out.println("User List::"+ u);
	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally{
		session.close();
	}
	return userList;
}



// using HQL to specify where condition for below 2 queries. 

public List<User> getRecByUsernameAndPassword(String username, String password){
	Session session = null;
	List<User> resultList = new ArrayList<User>();
	System.out.println("Inside function getRecByUsernameAndPassword");
	try{
		
		//session = HibernateUtil.getSession();

		
		
		session = sessionFactory.openSession();
		
		if(session == null){
			System.out.println("session is null");
		}
		else if(session != null){
			System.out.println("session is not null");
		}
			
		
	
		String hql = "FROM User user WHERE username = :username and password = :password";
		Query query = (Query)session.createQuery(hql);
		
		if(query == null){
			System.out.println("Query object is null");
		}
		else if(query != null)
		{
			System.out.println("Query object is not null");
		}
		
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		System.out.println("After setting query parameters");
		
				
		if(query.getResultList() == null)
		{
			System.out.println("query.getResultList is null");
			//return resultList;
		}
		else if(query.getResultList() != null){
			resultList = query.getResultList();
			System.out.println("query.getResultList is not null");
			//return resultList;
		}
		
		session.close();
		//session.flush();
		//sf.close();
		
		
		
	}
	catch(Exception e){
		e.printStackTrace();
		session.close();
		//session.flush();
		System.out.println("Finished closing session in catch block");
	}

	return resultList;
}





public List<User> getRecByUsername(String username){
		Session session = null;
		List<User> resultList = new ArrayList<User>();
		try{
		session = sessionFactory.openSession();	
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		//Criterion criterion1 = Restrictions.sizeGt("productPrice", 6);
		Criterion criterion = Restrictions.eq("username", username);
		dc.add(criterion);
	
		resultList = dc.getExecutableCriteria(session).list();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
	
		return resultList;

} // end of function


}
