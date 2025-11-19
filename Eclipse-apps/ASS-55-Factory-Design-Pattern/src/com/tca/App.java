package com.tca;

import com.tca.pojo.Car;
import com.tca.pojo.CarFactory;

public class App {
	public static void main(String[] args) {
		Car c = CarFactory.getCar();
		c.running();
		c.stop();
	}
}
