package com.glearning.hibernate.crud.util;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.glearning.hibernate.crud.model.Teacher;
import com.glearning.hibernate.crud.model.TeacherDetails;

/**
 * This class load the configuration file and creates SessionFactory.
 * SessionFactory is a Singleton
 * @author pradeep
 *
 */
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	//Create the session factory from the configuration and return the session factory
	public static final SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			/*
			 * List<Class<?>> classes = EntitySc .scanPackages("my.com.entities",
			 * "my.com.other.entities").result();
			 */			
			sessionFactory = new Configuration()
									.configure("hibernate-cfg.xml")
									.addAnnotatedClass(Teacher.class)
									.addAnnotatedClass(TeacherDetails.class)
									.buildSessionFactory();
		}
		return sessionFactory;
	}
}
