package com.tca.pojo;

/*
  In Singleton design pattern we only have one object of a specific class, 
  exists throughout the application's life-cycle
   
   for example, the SessionFactory, 
   In singleton design pattern we will have only one instance of SessionFactory 
 
 */


/*
 To demonstrate singleton design
 	
 	suppose, we use the car class through out the application lifecycle, 
 	i.e. when application starts we create the car object, and use it until the termination of the application
 
 	then in this scenario, we will instantiate car class only one time
 	
 	to achieve this, we have to stop the new operator for car class
 	this can be achieved by making constructors private
 	
 	if we can't use new then how to instantiate Car
 	to do that, we will use a method, who will create a car object, and give it to us
 	
 	but without creating object, how can we call a method ?
 	to do that, we will make that method static, so that we can call it using class name (Car)
 	
 	
 */

public class Car {

	private static Car car;
	
	private Car() {}  // now, we can't use new on Car class

	public static Car getInstance() {
		if(car == null)
			car = new Car();
		return car;
	}
	
	public void display() {
		System.out.println("Car.display()");
	}
	
}
