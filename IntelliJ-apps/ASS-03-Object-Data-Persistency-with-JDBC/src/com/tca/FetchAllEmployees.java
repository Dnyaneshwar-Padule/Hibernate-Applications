package com.tca;

import com.tca.dao.EmployeeDao;
import com.tca.entities.Employee;

import java.sql.SQLException;
import java.util.List;

public class FetchAllEmployees {

    public static void main(String[] args) {
        try{
            List<Employee> employees = EmployeeDao.fetchAll();

            if(employees.isEmpty()){
                System.out.println("Records not found !");
                return;
            }

            printTableHeader();
            for(Employee e : employees){
                System.out.printf("| %-6d | %-12s | %-10.2f |\n", e.getId(), e.getName(), e.getSalary());
            }
            printTableFooter();

            System.out.println("Done..............");
        }
        catch (SQLException se){
            System.out.println("Failed to execute query : " + se.getMessage());
        }
        catch ( NullPointerException ne ){
            System.out.println("Failed to form connection : " + ne.getMessage());
        }
        catch(Exception e){
            System.out.println("Something went wrong : " + e.getMessage());
        }
    }

    private static void printTableHeader(){
        System.out.println("+--------+--------------+------------+");
        System.out.println("|   ID   |     Name     |   Salary   | ");
        System.out.println("+--------+--------------+------------+");
    }

    private static void printTableFooter(){
        System.out.println("+--------+--------------+------------+");
    }
}
