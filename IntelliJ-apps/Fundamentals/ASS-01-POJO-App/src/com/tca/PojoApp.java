package com.tca;

import com.tca.entity.Employee;

public class PojoApp {

    public static void main(String[] args){
        Employee employee = new Employee();

        employee.setId(9272);
        employee.setName("Narendra");
        employee.setSalary(28000);

        System.out.println(employee);

    }
}
