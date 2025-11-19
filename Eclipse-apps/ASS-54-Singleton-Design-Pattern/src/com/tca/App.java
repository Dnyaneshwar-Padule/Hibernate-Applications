package com.tca;

import com.tca.pojo.Car;

public class App {

	public static void main(String[] args) {
		//Car c = new Car();  // not allowed 
	
		Car c1 = Car.getInstance();
		Car c2 = Car.getInstance();
		
		System.out.println(c1);
		System.out.println(c2);
		
		c1.display();
		c2.display();
	}
}
