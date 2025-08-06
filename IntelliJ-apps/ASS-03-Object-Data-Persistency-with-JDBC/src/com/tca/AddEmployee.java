package com.tca;

import com.sun.jdi.IntegerType;
import com.tca.dao.EmployeeDao;
import com.tca.entities.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class AddEmployee {

    public static void main(String[] args) {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            int count = 0;

            while(true){

                try{
                    System.out.print("\nEnter '1' to add new record : ");
                    if( Integer.parseInt(br.readLine())  != 1)
                        break;
                } catch (NumberFormatException e) {
                    break;
                }

                Employee employee = acceptEmployee(br);
                if (employee == null)
                    continue;

                try{
                    if(EmployeeDao.save(employee)){
                        count++;
                        System.out.println("Record is added successfully for employee ID : " + employee.getId());
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                    else{
                        System.out.println("Failed to add record for employee ID : " + employee.getId());
                        System.out.println("-------------------------------------------------------");
                    }
                }
                catch (SQLException se){
                    System.out.println("Failed to add record for employee ID : " + employee.getId());
                    System.out.println("-------------------------------------------------------");
                }
            }

            System.out.println(count + " record(s) saved !");
        }
        catch(IOException ie){
            System.out.println("Failed to detect input device.");
        }

    }

    private static Employee acceptEmployee(BufferedReader br) throws IOException{
        Employee employee = new Employee();

        try{
            System.out.print("Enter   the   ID : ");
            employee.setId(Integer.parseInt(br.readLine()));
        }
        catch (NumberFormatException ne){
            System.out.println(ne.getMessage() + " : Please enter valid data !");
            System.out.println("*******************************************************");
            return null;
        }

        System.out.print("Enter  the  name : ");
        employee.setName(br.readLine());

        try{
            System.out.print("Enter the salary : ");
            employee.setSalary(Double.parseDouble(br.readLine()));
        } catch (NumberFormatException ne) {
            System.out.println(ne.getMessage() + " : Please enter valid data !");
            System.out.println("*******************************************************");
            return null;
        }

        return employee;
    }

}


