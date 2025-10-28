package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

/**
 * Student, an entity class
 */
@Entity
@Table(name="student")
public class Student {
	
	/*
	 * We use table strategy, if the database does not support any sequence, identity or auto_increment
	 * 
	 * it creates an extra table to generate primary keys
	 * 
	 * for example, the below table generator will create this table 
	 * table name = pk_generator
	 * 
	 * +--------+----------+
	 * | entity | pk_value |
	 * +--------+----------+
	 * | st_pk  |        1 |   -- 1 is the initial Value
	 * +--------+----------+   -- we can use this single table for primary key generation for multiple entities 
	 * 
	 * 
	 * entity column in PRIMARY KEY of the pk_generator table
	 * 
	 * hibernate, uses the value in entity table, to fetch the primary key value 
	 * from pk_value column for a specific entity
	 * 
	 * like for example for student entity, the primary keys are in pk_value where entity = 'std_pk'
	 * 
	 * means, get the Primary key value from the pk_generator table where entity is std_pk
	 *
	 * 
	 * If it's used for multiple entities , it will look like this
	 * +-------------+----------+
	 * |   entity    | pk_value |
	 * +-------------+----------+
	 * | student_pk  |        1 | 
	 * | teacher_pk  |      101 | 
	 * | employee_pk |     1234 | 
	 * +-------------+----------+   -- we can use this single table for primary key generation for multiple entities 
	 * 
	 */
	
	@Id
	@TableGenerator(
			name="pk_generator", // hibernate specific name (primark key generator)
			table = "pk_generator", // actual table name in database 
			pkColumnName = "entity", // the primary key  column name, used to fetch and update the actual primary key value 
		    pkColumnValue = "std_pk", // Hibernate will fetch the value with this primay key (primary key value), for this entity (student entity)
		    valueColumnName = "pk_value", // The actual primary key value will reside in this column
		    initialValue = 1, // the initial value, the primary keys will start from this table
		    allocationSize = 1 // allocation size, (default is 50)
			)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "pk_generator") // AUTO, INDENTITY, SEQUENCE,TABLE  
	@Column(name="rno")
	private Integer rno;

	 //To store name of the student
	@Column(name="name", nullable = false)
	private String name;
	
	// To store percentage of the student
	@Column(name="per", columnDefinition = "FLOAT CHECK (per >= 0 and per <= 100)")
	private Double per;


	// setters and getters
	public Integer getRno() {
		return rno;
	}

	public void setRno(Integer rno) {
		this.rno = rno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPer() {
		return per;
	}

	public void setPer(Double per) {
		this.per = per;
	}

	//toString() method
	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + "]";
	}
		
}
