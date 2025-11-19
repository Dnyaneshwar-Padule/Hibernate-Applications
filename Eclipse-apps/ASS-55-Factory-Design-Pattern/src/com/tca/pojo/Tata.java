package com.tca.pojo;

public class Tata implements Car {

	@Override
	public void running() {
		System.out.println("Tata.running()");
	}

	@Override
	public void stop() {
		System.out.println("Tata.stop()");
	}

}
