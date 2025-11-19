package com.tca.pojo;

public class CarFactory {

	private static Car car ;
	
	private CarFactory() {}
	
	public static Car getCar() {
		if(car == null) {
			//car = new Tata();
			car = new Mahindra(); // by doing one small change, we are using another implementation, all over the application
		}
		return car;
	}
	
	
}
