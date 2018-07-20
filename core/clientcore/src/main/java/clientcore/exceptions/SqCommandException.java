package clientcore.exceptions;

import common.exceptions.ErrorCode;

public class SqCommandException extends Exception{
	
	private static final long serialVersionUID = 7718828512143293559L;
	
	private final ErrorCode code;

	public SqCommandException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqCommandException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqCommandException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqCommandException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}

}
