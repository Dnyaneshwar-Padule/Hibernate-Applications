package com.tca.dao;

import com.tca.entities.Employee;
import com.tca.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public static boolean save(Employee employee) throws SQLException {

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO employee VALUES(?,?,?)")){

            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setDouble(3, employee.getSalary());

            int sval = ps.executeUpdate();
            if (sval == 1)
                return true;
        }
        return false;
    }

    public static List<Employee> fetchAll() throws SQLException, NullPointerException{
        List<Employee> employees = new ArrayList<>();

        try(Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                employees.add( mapResultSet(rs) );
            }
        }
        return employees;
    }

    private static Employee mapResultSet(ResultSet rs) throws SQLException{
        Employee employee = new Employee();
        employee.setId( rs.getInt("id") );
        employee.setName( rs.getString("name") );
        employee.setSalary( rs.getDouble("salary") );
        return employee;
    }

}
