package com.glearning.hibernate.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.glearning.hibernate.crud.model.Teacher;
import com.glearning.hibernate.crud.model.TeacherDetails;
import com.glearning.hibernate.crud.util.HibernateUtil;

/**
 * Has the dependency on the SessionFactory
 * 
 *
 */
public class TeacherService {

	private final SessionFactory sessionFactory;
	private Session session;

	public TeacherService() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	/**
	 * Template to work with Hibernate 1. Use the sesssionfactory to fetch a session
	 * 2. From the session, begin a transaction 3. Execute the hibernate 4. Commit
	 * the hibernate transaction 5. Close the transaction 6. Close the session
	 */
	public Teacher insertTeacherRecord(Teacher teacher, TeacherDetails teacherDetails) {
		try {
			session = sessionFactory.openSession();
			// From the session, begin a transaction
			Transaction transaction = session.beginTransaction();
			teacher.addTeacherDetails(teacherDetails);
			Long result = (Long)session.save(teacher);
			System.out.println("The primary key of the stored teacher instance:: "+ result);
			transaction.commit();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return teacher;
	}

	public List<Teacher> findAllTeachers() {
		List<Teacher> teachers  = new ArrayList();
		try {
			session = sessionFactory.openSession();
			// From the session, begin a transaction
			Transaction transaction = session.beginTransaction();
			teachers = session.createQuery("from Teacher").list();
			transaction.commit();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return teachers;
	}

	public Teacher findTeacherByTeacherId(long teacherId) {
		Teacher teacher = null;
		try {
			session = sessionFactory.openSession();
			// From the session, begin a transaction
			Transaction transaction = session.beginTransaction();
			teacher = session.get(Teacher.class, teacherId);
			transaction.commit();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} finally {
			session.close();
		}
		return teacher;
	}

	public void deleteTeacherRecordByTeacherId(long teacherId) {
		Teacher teacher = null;
		try {
			session = sessionFactory.openSession();
			// From the session, begin a transaction
			Transaction transaction = session.beginTransaction();
			//transaction boundary
			//===============persistence context/first level cache - starts=====================================
			teacher = session.get(Teacher.class, teacherId);
			session.delete(teacher);
			transaction.commit();
			//===============persistence context/first level cache - ends=====================================
			// end of the transaction
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		} finally {
			session.close();
		}
	}

}
