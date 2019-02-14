package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {
		
		//CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			//START A TRANSACTION
			session.beginTransaction();
			
			// CREATE A COURSE
			Course tempCourse = new Course("Pacman - How to score 1 millon points");
			
			//SAVE COURSE - CASCADE
			session.save(tempCourse);
			
			//CREATE STUDENTS
			Student tempStudentOne = new Student("Rodolfo", "Carvajal", "rodolfojcarvajalm@gmail.com");
			Student tempStudentTwo = new Student("Cesar", "Padron", "cesarpadron@gmail.com");
			
			//ADD STUDENT TO THE COURSE
			tempCourse.addStudent(tempStudentOne);
			tempCourse.addStudent(tempStudentTwo);
			
			//SAVE STUDENT
			System.out.println("Saving students....");
			session.save(tempStudentOne);
			session.save(tempStudentTwo);
			
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
