package net.kzn.collaboration.exception;
public class FieldPolicyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public FieldPolicyException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
