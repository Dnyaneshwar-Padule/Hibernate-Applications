package com.tca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="account")
public class Account {

	@Id
	@Column(name="account_no")
	private Integer accountNo;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="amount", columnDefinition = "FLOAT CHECK(amount > 500)")
	private Float amount;

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
}

/*
 	
 	Database table
 	
 	CREATE TABLE account(
 		account_no int PRIMARY KEY,
 		name varchar(32) NOT NULL,
 		amount float CHECK(amount > 50)
 	);
 	
 	INSERT INTO account values(1001, 'Athrva', 70000);
 	INSERT INTO account values(1002, 'Prasad', 5000);
 
 * */