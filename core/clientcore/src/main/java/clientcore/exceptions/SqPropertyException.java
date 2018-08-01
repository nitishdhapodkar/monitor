package clientcore.exceptions;

import common.constants.ErrorCode;

public class SqPropertyException extends Exception{
	
	private static final long serialVersionUID = -3597511029617115564L;
	private final ErrorCode code;

	public SqPropertyException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqPropertyException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqPropertyException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqPropertyException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}
}
