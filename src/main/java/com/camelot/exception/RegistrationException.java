package com.camelot.exception;

public class RegistrationException extends RuntimeException {

	private static final long serialVersionUID = -5953508631530292318L;

	public RegistrationException(String arg0) {
		super(arg0);
	}

	public RegistrationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
