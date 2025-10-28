package com.tca.entities;

/**
 * An entity class which is mapped with "employee" table in the database
 * <p>
 *     It holds employee data like employee id (primary key in the table),
 *     employee name and employee salary .
 *     It also provides standard getter and setter methods for hibernate and other
 *     application level operations.
 * </p>
 */
public class Employee {

    /**
     * Unique employee ID, Primary key
     */
    private Integer id;

    /**
     * Full name of an employee
     */
    private String name;

    /**
     * Salary of an employee
     * (It should be an unsigned double value)
     */
    private Double salary;

    /**
     * Returns employee ID
     * @return id {@link Integer}
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the employee ID
     * @param id {@link Integer}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of an employee
     * @return name {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of an employee
     * @param name {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the salary of an employee
     * @return salary {@link Double}
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of an employee
     * (Should be unsigned double value)
     * @param salary {@link Double}
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
