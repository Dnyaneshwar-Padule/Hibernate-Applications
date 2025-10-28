package com.tca.entities;

public class Employee {

    private Integer tno;
    private String name;
    private Double salary;

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer rno) {
        this.tno = rno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "tno=" + tno + ", name='" + name + '\'' + ", salary=" + salary + '}';
    }
}
