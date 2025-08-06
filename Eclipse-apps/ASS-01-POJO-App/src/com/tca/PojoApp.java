package com.tca;

import com.tca.entity.Student;

public class PojoApp {

	public static void main(String[] args) {
		
		Student student = new Student();
		
		student.setRno(49);
		student.setName("Dnyaneshwar");
		student.setPer(90.23f);
		
		System.out.println(student);
	}

}
