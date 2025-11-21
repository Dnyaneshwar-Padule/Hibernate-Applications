package com.tca.controller;

import com.tca.exception.AppException;
import com.tca.exception.DatabaseException;
import com.tca.exception.NotFoundException;
import com.tca.exception.ValidationException;

public class ExceptionController {

	private ExceptionController() {}
	
	public static void handel(Exception e) {
		if(e instanceof NotFoundException) {
			System.out.println("Not Found: " + e.getMessage());
		}
		else if(e instanceof AppException) {
			System.out.println("Application error: " + e.getMessage());
		}
		else if(e instanceof DatabaseException) {
			System.out.println("Database Failure: " + e.getMessage());
		}
		else if( e instanceof ValidationException) {
			System.out.println(e.getMessage());
		}
		else {
			System.out.println("Unexpected Error: " + e.getMessage());
		}
	}
	
}
