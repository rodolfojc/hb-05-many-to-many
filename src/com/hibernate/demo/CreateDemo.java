package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

public class CreateDemo {

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
			
			//CREATE THE OBJECTS
			Instructor tempInstructor = 
					new Instructor("Pedro", "Alvarez", "pedro@pedro.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://example.com", "Coder");
			
			//ASSOCIATE THE OBJECTS
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//START A TRANSACTION
			session.beginTransaction();
			
			//SAVE INTRUCTOR - ALSO SAVE THE DETAILS OBJECT - CASCADETYPE.ALL
			System.out.print("Saving instructor: "+tempInstructor);
			session.save(tempInstructor);
						
			//COMMIT TRANSACTION
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
		
	}

}
