package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		//CREATE SESSION FACTORY
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		//CREATE SESSION
		Session session = factory.getCurrentSession();
		
		try {
			
			//START A TRANSACTION
			session.beginTransaction();
			
			// CREATE A COURSE
			Course tempCourse = new Course("Pacman - How to score 1 millon points");
			
			//ADD SOME REVIEWS
			tempCourse.addReview(new Review("Great course"));
			tempCourse.addReview(new Review("Amazing Game"));
			tempCourse.addReview(new Review("You will love it"));
			
			//SAVE COURSE - CASCADE
			System.out.println("Saving course! "+tempCourse);
			session.save(tempCourse);
			
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
