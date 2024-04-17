package com.myapp.exception;

public class WorkFlowException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public WorkFlowException(String message) {
		
		super(message);
	}
	public WorkFlowException(String message,Throwable t) {
		
		super(message,t);
	}
}
