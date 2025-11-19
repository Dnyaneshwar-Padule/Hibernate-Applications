package com.tca.pojo;

public class Mahindra implements Car {

	@Override
	public void running() {
		System.out.println("Mahindra.running()");
	}

	@Override
	public void stop() {
		System.out.println("Mahindra.stop()");
	}

}
