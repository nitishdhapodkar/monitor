package common.exceptions;

import common.constants.ErrorCode;

public class SqCronResolverException extends Exception{
	
	private static final long serialVersionUID = 7956237336621896552L;
	private final ErrorCode code;

	public SqCronResolverException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqCronResolverException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqCronResolverException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqCronResolverException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}
}
