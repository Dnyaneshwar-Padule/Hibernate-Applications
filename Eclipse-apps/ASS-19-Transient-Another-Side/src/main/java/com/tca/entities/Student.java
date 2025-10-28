package com.tca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 
 * If database table has extra columns, which are not defined in the entity class as field 
 * suppose, database table has rno, name, per, email, grade
 * and entity class has rno, name, per, email (no grade field)
 * Then when we insert a student record in the table, the grade field sets to null
 * 
 * Then who will put value in the grade column ?
 * We can derive grade from percentage, so we can use a stored procedure 
 * we will create before trigger on student table, which will get fired at time of insertion
 * it will internally fire stored SQL and will put value for it.
 * 
 * Example of Trigger...
 
 --Student table made by database administrator (team)
 CREATE TABLE STUDENT(
 	rno int PRIMARY KEY,
 	name varchar(32) NOT NULL,
 	per float,
 	email varchar(32) UNIQUE NOT NULL,
 	grade varchar(1)
 );
 
 
 --Function which will executed by the trigger
  CREATE OR REPLACE FUNCTION insrt_trg() 
  RETURNS TRIGGER AS $$
  BEGIN 
  
  	if NEW.per >= 90 then
  		NEW.grade := 'A';
  	elsif NEW.per >= 80 then
  		NEW.grade := 'B';
  	elsif NEW.per >= 70 then
  		NEW.grade := 'C';
  	elsif NEW.per >= 60 then
  		NEW.grade := 'D';
  	elsif NEW.per >= 40 then
  		NEW.grade := 'E';
  	else
  		NEW.grade := 'F';
  	end if;
  	
  	return NEW;
  
  END;
  $$ LANGUAGE 'plpgsql';
  
  --Trigger on student Table
  CREATE TRIGGER insert_trg
  BEFORE INSERT or UPDATE
  ON student
  FOR EACH ROW
  EXECUTE PROCEDURE insrt_trg();
  
 * 
 */

@Entity
@Table(name="student")
public class Student {

	//Primary Key
	@Id
	@Column(name="rno")
	private Integer rno;

	@Column(name="name", nullable = false) // name should not be null
	private String name;
	
	@Column(name="per", columnDefinition = "FLOAT CHECK(per >= 0 and per <= 100)")
	private Double per;
	
	@Column(name="email", nullable = false, unique = true) // email should not be null, and should be unique
	private String email;
	
		
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + ", email=" + email +  "]";
	}
	
	
}
