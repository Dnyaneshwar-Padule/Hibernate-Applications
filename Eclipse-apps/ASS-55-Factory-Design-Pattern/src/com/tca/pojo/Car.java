package com.tca.pojo;


/*
 	Now, suppose java want to create a technology, which will help java to communicate with databases
 	but, java don't know internals of databases, whose vendors are different (oracle, postgreSQL)
 	
 	so, they will create an interface, telling database vendors, if you want java to communicate with your databases
 	You have to use these methods or rules 
 	like they have to implement connect method, close() method executeUpdate() 
 	
 	this will make JDBC reliable, i.e. If we any database, the methods and classes will remain same
 	
 	In Factory design pattern we use loose coupling
 	i.e. we will create the object of a class which implement Car interface
 	and we will put that object's reference in Car interface
 	
 	so that, in future if we use another implementation, we have to make only few changes, not all
  
 */

public interface Car {

	public abstract void running();
	public abstract void stop();
}
