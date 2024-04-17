package com.myapp.exception;

public class InvalidInputParameterException extends RuntimeException {

	private static final long serialVersionUID = 3027866666924413221L;

	public InvalidInputParameterException(String message, Throwable t) {
		super(message, t);
	}

	public InvalidInputParameterException(String message) {
		super(message);
	}

	public Throwable getRootCause() {
		Throwable	e	= getCause();
		Throwable	eParent;
		for (eParent = this; e != null && e != eParent; e = e.getCause())
			eParent = e;

		return eParent;
	}
}
