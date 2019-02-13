package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		//CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			//CREATE THE OBJECTS
			Instructor tempInstructor = 
					new Instructor("Lucy", "Marquez", "Lucy@pedro.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://example.com", "Gamer");
			
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
			//ADD CLEAN UP CODE
			session.close();
			
			factory.close();
		}
		
		
	}

}
