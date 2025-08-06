package com.tca;

import java.sql.SQLException;
import java.util.List;

import com.tca.dao.StudentDao;
import com.tca.entities.Student;

public class FetchAllStudents {

	public static void main(String[] args) {
		
		try {
			List<Student> students = StudentDao.fetchAll();
			
			if(students.isEmpty()) {
				System.out.println("Records are not found !");
				return;
			}
			
			displayTableHeader();
			for( Student s : students ) {
				System.out.printf("| %-11d | %-12s | %-10.2f |\n", s.getRno(), s.getName(), s.getPer());
			}
			closeTable();
		
			System.out.println("Done...........");
		}
		catch(SQLException se) {
			System.out.println( se.getMessage() +  " : Failed to execute query...");
		}
		catch(NullPointerException ne) {
			System.out.println("Failed to form connection : " + ne.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + " : Something went wrong !");
		}
		
	}
	
	private static void displayTableHeader() {
		System.out.printf("+-------------+--------------+------------+\n");
		System.out.printf("| Roll Number |     Name     | Percentage |\n");
		System.out.printf("+-------------+--------------+------------+\n");
	}
	
	private static void closeTable() {
		System.out.printf("+-------------+--------------+------------+\n");		
	}

}
