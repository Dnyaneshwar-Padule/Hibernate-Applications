package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="executive_2")
public class Executive extends Person {

	@Column(name="role")
	private String role;
	
	@Column(name="bonus", columnDefinition = "FLOAT")
	private Double bonus;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	
	
	
}
