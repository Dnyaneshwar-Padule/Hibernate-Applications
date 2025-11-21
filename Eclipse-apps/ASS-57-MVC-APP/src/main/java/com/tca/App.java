package com.tca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.tca.controller.ExceptionController;
import com.tca.entity.Course;
import com.tca.entity.Student;
import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.exception.NotFoundException;
import com.tca.factory.CourseServiceFactory;
import com.tca.factory.StudentServiceFactory;
import com.tca.service.StudentService;
import com.tca.util.HibernateUtil;

public class App {
    public static void main(String[] args) {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    	// label for loop
    	outerloop:
    	while(true) {
    		try {
    			System.out.println("\n    *** Menu ***");
    			System.out.println(" 1. Save new student record");
    			System.out.println(" 2. Update student record");
    			System.out.println(" 3. Delete student record");
    			System.out.println(" 4. See all student records");
    			System.out.println(" 5. Search student record by roll no");	
    			System.out.println(" 6. Search student record by name");	
    			System.out.println(" 7. Search student record by city");
    			System.out.println("    ----------------------------");
    			System.out.println(" 8. Save new Course");
    			System.out.println(" 9. Update Course");
    			System.out.println("10. Delete Course");
    			System.out.println("11. See all Courses");
    			System.out.println("12. Search course by course no.");
    			System.out.println("13. Search course by course name");
    			System.out.println("    ----------------------------");
    			System.out.println("14. Add new registration ");
    			System.out.println("15. See Course wise list of students");
    			System.out.println("16. See Student wise list of courses");
    			System.out.println("17. Exit");
    			System.out.print("Enter your choice : ");
    			
    			switch( Integer.parseInt(br.readLine()) ) {
    			case 1:
    				optionSaveStudent();
    				break;
    			case 2:
    				optionUpdateStudent();
    				break;
    			case 3:
    				optionDeleteStudent();
    				break;
    			case 4:
    				optionGetAllStudents();
    				break;
    			case 5:
    				optionGetStudentByRno();
    				break;
    			case 6:
    				optionGetStudentByName();
    				break;
    			case 7:
    				optionGetStudentByCity();
    				break;
    			case 17:
    		   		break outerloop;
    			default:
    				System.out.println("Invalid input !");
    			}
    		}
    		catch(NumberFormatException ne) {
    			System.out.println("Please enter valid choice !");
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    			break;
    		}
    	}
    	
    	try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	HibernateUtil.closeSessionFactory();
   		System.out.println("Done...");
    }
    
    private static void optionAddNewRegistration() {
    	
    }
    
    
    private static void optionSearchCourseByName() {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		System.out.print("Enter the Course name : ");
    		String courseName =  br.readLine();
    		List<Course> courses =  CourseServiceFactory.getCourseServiceInstance().getCourseByName(courseName);
    		if(courses == null) {
    			System.out.println("No courses found !");
    		}
    		else {
    			System.out.println("***  Courses  ***");
        		for(Course c : courses) {
        			System.out.println("Course  no  :" + c.getCno());
        			System.out.println("Course name :" + c.getName());
        		}
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching course !");	
    	}
    }
    
    private static void optionSearchCourseByCno() {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		System.out.print("Enter the Course no : ");
    		Integer cno = Integer.parseInt( br.readLine() );
    		Course course = CourseServiceFactory.getCourseServiceInstance().getCourse(cno);
    		if(course == null) {
    			System.out.println("Course does not exists with course number : " + cno);
    		}
    		else {
    			System.out.println("Course  no  :" + course.getCno());
    			System.out.println("Course name :" + course.getName());
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching course !");	
    	}
    }
    
    private static void optionSeeAllCourses() {
    	try {
    		List<Course> courses = CourseServiceFactory.getCourseServiceInstance().getAllCourses();
    		if(courses == null || courses.size() < 1) {
    			System.out.println("No courses found !");
    		}
    		else {
    			System.out.println("***  Courses  ***");
    			for(Course c : courses) {
    				System.out.println("Course  no  :" + c.getCno());
    				System.out.println("Course name :" + c.getName());
    			}    			
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching courses !");    		
    	}
    }
    
    private static void optionDeleteCourse() {
    	BufferedReader br = new BufferedReader( new InputStreamReader( System.in));
    	try {
    		System.out.print("Enter the course number : ");
    		Integer cno = Integer.parseInt(br.readLine());
    		if (  CourseServiceFactory.getCourseServiceInstance().updateCourse(cno) ) {
    			System.out.println("Record is deleted successfully !");
    		}
    		else {
    			System.out.println("Unable to delete record !");
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(NotFoundException ne) {
    		ExceptionController.handel(ne);
    	}
    	catch(AppException ae) {
    		ExceptionController.handel(ae);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while deleting course record !");
    	}
    }
    
    private static void optionUpdateCourse() {
    	BufferedReader br = new BufferedReader( new InputStreamReader( System.in));
    	try {
    		System.out.print("Enter the course number : ");
    		Integer cno = Integer.parseInt(br.readLine());
    		if (  CourseServiceFactory.getCourseServiceInstance().updateCourse(cno) ) {
    			System.out.println("Record is updated successfully !");
    		}
    		else {
    			System.out.println("Unable to update record !");
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(NotFoundException ne) {
    		ExceptionController.handel(ne);
    	}
    	catch(AppException ae) {
    		ExceptionController.handel(ae);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while updating course record !");
    	}
    }
    
    private static void optionSaveNewCourse() {
    	BufferedReader br = new BufferedReader( new InputStreamReader( System.in));
    	try {
    		Course c = new Course();
    		System.out.print("Enter the course name : ");
    		c.setName( br.readLine() );
    		Integer cno = CourseServiceFactory.getCourseServiceInstance().addCourse(c);
    		if(cno == null) {
    			System.out.println("Unable to save course !");
    		}
    		else {
    			System.out.println("Course is saved with course no : " + cno);
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while saving course record !");
    	}
    }
    
    private static void optionGetStudentByRno() {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		System.out.print("Enter the roll number of the student : ");
    		Student student = StudentServiceFactory
    				.getStudentServiceInstance()
    				.getStudent( Integer.parseInt(br.readLine()) );
    		if(student == null) {
    			System.out.println("No record found !");
    		}
    		else {
    			System.out.println("** Student Data **");
    			System.out.println("Student    name    : " + student.getName());
    			System.out.println("Student    city    : " + student.getCity());
    			System.out.println("Student percentage : " + student.getPer());
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(NumberFormatException ne) {
    		System.out.println("Please enter valid roll number !");
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching student record !");
    	}
    }
    
    private static void optionDeleteStudent() {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		System.out.print("Enter the roll number of the student : ");
    		if( StudentServiceFactory.getStudentServiceInstance().deleteStudent( Integer.parseInt(br.readLine()) ) ) 
    			System.out.println("Student record is deleted successfully !");
    	}
    	catch(AppException ae) {
    		ExceptionController.handel(ae);
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(NumberFormatException ne) {
    		System.out.println("Please enter valid roll number !");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("Problem while deleting student record !");
    	}
    }
    
    private static void optionUpdateStudent() {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		System.out.print("Enter the roll number of the student : ");
    		if( StudentServiceFactory.getStudentServiceInstance().updateStudent( Integer.parseInt(br.readLine()) ) ) 
    			System.out.println("Student record is updated successfully !");
    	}
    	catch(AppException ae) {
    		ExceptionController.handel(ae);
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(NumberFormatException e) {
    		System.out.println("Please enter valid roll number !");
    	}
    	catch(Exception e) {
    		System.out.println("Problem while updating student record !");
    	}
    }
    
    private static void optionGetStudentByCity() {
    	BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
    	try
    	{
    		System.out.print("Enter name of the city : ");
    		List<Student> students = StudentServiceFactory
    				.getStudentServiceInstance()
    				.getStudentsByCity( br.readLine() );
    		
    		if(students == null || students.size() == 0) {
    			System.out.println("No records found !");
    		}
    		else {
    			System.out.println("** Student Records **");
    			for(Student s : students) {
    				System.out.println("Student  Roll no   : " + s.getRno());
    				System.out.println("Student     Name   : " + s.getName());
    				System.out.println("Student Percentage : " + s.getPer());
    				System.out.println("Student     City   : " + s.getCity());    				
    				System.out.println("");
    			}
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching data !");
    	}
    }
    
    private static void optionGetStudentByName() {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try
    	{
    		System.out.print("Enter name of the student : ");
    		List<Student> students = StudentServiceFactory
    				.getStudentServiceInstance()
    				.getStudentsByName( br.readLine() );
    		
    		if(students == null || students.size() == 0) {
    			System.out.println("No records found !");
    		}
    		else {
    			System.out.println("** Student Records **");
    			for(Student s : students) {
    				System.out.println("Student  Roll no   : " + s.getRno());
    				System.out.println("Student     Name   : " + s.getName());
    				System.out.println("Student Percentage : " + s.getPer());
    				System.out.println("Student     City   : " + s.getCity());    				
    				System.out.println("");
    			}
    		}
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching data !");
    	}
    }
    
    private static void optionGetAllStudents() {
    	try {
    		List<Student> students = StudentServiceFactory
    				.getStudentServiceInstance()
    				.getAllStudents();
    		
    		if(students == null || students.size() == 0) {
    			System.out.println("No records found !");
    		}
    		else {
    			System.out.println("** Student Records **");
    			for(Student s : students) {
    				System.out.println("Student  Roll no   : " + s.getRno());
    				System.out.println("Student     Name   : " + s.getName());
    				System.out.println("Student Percentage : " + s.getPer());
    				System.out.println("Student     City   : " + s.getCity());    				
    				System.out.println("");
    			}
    		}
    		
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while fetching data !");
    	}
    }
    
    private static void optionSaveStudent() {    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		StudentService studentService = StudentServiceFactory.getStudentServiceInstance();
    		
    		Student s = new Student();
    		
    		System.out.print("Enter    name    :");
    		s.setName( br.readLine().trim() );
    		
    		System.out.print("Enter percentage :");
    		s.setPer(Double.parseDouble(br.readLine()));
    		
    		System.out.print("Enter    city    :");
    		s.setCity( br.readLine().trim() );

    		Integer id = studentService.addStudent(s);
    		
    		if(id == null) {
    			System.out.println("Unable to save student record !");
    		}
    		else {
    			System.out.println("Student is saved with roll number : " + id);
    		}
    		
    	}
    	catch(DatabaseException de) {
    		ExceptionController.handel(de);
    	}
    	catch(NumberFormatException ne) {
    		System.out.println("Enter valid percentage !");
    	}
    	catch(Exception e) {
    		System.out.println("Something went wrong while saving data !");
    	}
    }
}
