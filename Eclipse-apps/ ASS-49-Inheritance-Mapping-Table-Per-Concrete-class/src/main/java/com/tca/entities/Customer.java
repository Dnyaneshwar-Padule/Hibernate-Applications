package com.tca.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	/*
	 	Cascade all - If one customer is removed, remove his all orders
	 	lazy fetch type - don't fetch orders eagarly, fetch them only if needed
	 	mapped by - Orders table madhye, konta column customer chya id store kartoy
	 		or, Orders Class madhye, konti field Customer cha data store kartiy
	 */
	@OneToMany(
			cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY, 
			mappedBy = "customer"
		)
	List<Orders> orders;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Orders> getOrders() {
		return orders;
	}


	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
}
