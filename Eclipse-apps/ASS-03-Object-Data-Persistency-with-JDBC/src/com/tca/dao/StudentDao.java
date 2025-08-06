package com.tca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tca.entities.Student;
import com.tca.util.DBUtil;

public class StudentDao {

	public static boolean save(Student student) throws SQLException{
		
		try (Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO students VALUES(?,?,?)") ){
			
			ps.setInt(1, student.getRno());
			ps.setString(2, student.getName());
			ps.setDouble(3, student.getPer());
			
			int sval = ps.executeUpdate();
			
			if(sval == 1) 
				return true;
		}
		
		return false;
	}
	
	public static List<Student> fetchAll() throws SQLException, NullPointerException{
		List<Student> students = new ArrayList<>();
		
		try(Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM students")){
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Student student = mapResultSet(rs);
				if(student != null) {
					students.add(student);					
				}
			}			
		}
		
		return students;
	}
	
	private static Student mapResultSet(ResultSet rs) {
		Student student = new Student();
		try {
			student.setRno( rs.getInt("rno") );
			student.setName( rs.getString("name") );
			student.setPer( rs.getDouble("per") );			
			return student;
		}catch(Exception e) { 
			return null;
		}
	}
	
}
