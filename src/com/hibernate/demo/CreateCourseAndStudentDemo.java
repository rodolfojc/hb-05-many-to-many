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
			
			//GET RODOLFO FROM THE DATABASE
			int studentId = 1;
			
			Student tempStudent = session.get(Student.class, studentId);
			System.out.println("\nLoading courses...");
			System.out.println("Courses: "+tempStudent.getCourses());
			
			//CREATE COURSES
			Course tempCourseOne = new Course("N64 - Best strategies");
			Course tempCourseTwo = new Course("Atari 2600 - Games");
			
			//ADD STUDENT TO THE COURSE
			tempCourseOne.addStudent(tempStudent);
			tempCourseTwo.addStudent(tempStudent);
			
			//SAVE COURSES
			session.save(tempCourseOne);
			session.save(tempCourseTwo);
			
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
