package com.tca.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String city;
	private Integer pinCode;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	
}
