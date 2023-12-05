package org.jsp.adminbusapp.exception;

public class BusNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6523953905701585351L;

	public BusNotFoundException(String message) {
		super(message);
	}
}
