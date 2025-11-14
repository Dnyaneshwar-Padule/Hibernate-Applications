package com.tca.entities;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
//@FilterDef(name="cityFilter", parameters = @ParamDef(name = "targetCity",  type = String.class))
//@Filter(name="cityFilter", condition = "city = :targetCity")
//@FilterDef(name="perFilter", parameters = @ParamDef(name="minPer", type = Double.class))
//@Filter(name="perFilter", condition = "per >= :minPer")

@FilterDefs(
				{
					@FilterDef(name="cityFilter", parameters = @ParamDef(name = "targetCity",  type = String.class)),
					@FilterDef(name="perFilter", parameters = @ParamDef(name="minPer", type = Double.class))
				}
			)

@Filters(
			{
				@Filter(name="cityFilter", condition = "city = :targetCity"),
				@Filter(name="perFilter", condition = "per >= :minPer")
			}
		)

public class Student{
	
	@Id
	@Column(name="rno")
	private Integer rno;

	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="per", columnDefinition = "FLOAT CHECK(per >= 0 and per <= 100)")
	private Double per;
	
	@Column(name="city", nullable = false)
	private String city;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}