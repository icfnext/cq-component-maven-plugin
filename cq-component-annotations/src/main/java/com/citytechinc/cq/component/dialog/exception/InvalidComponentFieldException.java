package com.citytechinc.cq.component.dialog.exception;

public class InvalidComponentFieldException extends Exception {

	private static final long serialVersionUID = 2950333347422459059L;

	public InvalidComponentFieldException() {
		super();
	}

	public InvalidComponentFieldException(String reason) {
		super(reason);
	}

	public InvalidComponentFieldException(String reason, Exception e) {
		super(reason, e);
	}
}
