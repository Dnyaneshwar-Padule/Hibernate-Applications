package com.tca.entities;

/**
 * Represents a Student entity mapped to the "student" table.
 * <p>
 * This class holds details about a student such as roll number, name, 
 * and percentage. It is used in ORM mappings and application logic.
 */
public class Student {

    /**
     * Roll number of the student (Primary Key).
     */
    private Integer rno;

    /**
     * Full name of the student.
     */
    private String name;

    /**
     * Percentage score of the student.
     */
    private Double per;

    /**
     * Returns the roll number of the student.
     *
     * @return roll number as {@link Integer}
     */
    public Integer getRno() {
        return rno;
    }

    /**
     * Sets the roll number of the student.
     *
     * @param rno roll number to set
     */
    public void setRno(Integer rno) {
        this.rno = rno;
    }

    /**
     * Returns the full name of the student.
     *
     * @return student's name as {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the student.
     *
     * @param name student's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the percentage score of the student.
     *
     * @return percentage as {@link Double}
     */
    public Double getPer() {
        return per;
    }

    /**
     * Sets the percentage score of the student.
     *
     * @param per percentage to set
     */
    public void setPer(Double per) {
        this.per = per;
    }

	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + "]";
	}
    
}
