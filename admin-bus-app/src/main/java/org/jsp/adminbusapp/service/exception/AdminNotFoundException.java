package org.jsp.adminbusapp.service.exception;

public class AdminNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4722790333048525086L;

	@Override
	public String getMessage() {
		return "Can't Get Admin";
	}
}
