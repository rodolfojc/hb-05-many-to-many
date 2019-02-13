package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		//CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			//START A TRANSACTION
			session.beginTransaction();
			
			//GET INTRUCTOR DETAIL OBJECT
			int theId = 999;
			InstructorDetail temInstructorDetail = session.get(InstructorDetail.class, theId);			
			
			//PRINT THE INSTRUCTOR DETAIL
			System.out.println("Instructor: "+temInstructorDetail);
						
			//PRINT THE ASSOCIATED INSTRUCTOR
			System.out.println("Associated instructor: "+temInstructorDetail.getInstructor());
						
			//COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			//HADDLE CONNECTION LEAK ISSUE
			session.close();
			
			factory.close();
		}
		
		
	}

}
