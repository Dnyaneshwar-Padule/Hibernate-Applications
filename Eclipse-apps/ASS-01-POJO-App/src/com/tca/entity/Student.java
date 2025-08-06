package com.tca.entity;

public class Student {

	private int rno;
	private String name;
	private float per;
	
	public Student() {}
	
	public int getRno() {
		return rno;
	}
	
	public void setRno(int rno) {
		this.rno = rno;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPer() {
		return per;
	}
	
	public void setPer(float per) {
		this.per = per;
	}
	
	@Override
	public String toString() {
		return "Student [rno=" + rno + ", name=" + name + ", per=" + per + "]";
	}
	
	
}
