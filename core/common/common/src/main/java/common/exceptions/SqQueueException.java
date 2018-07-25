package common.exceptions;

import common.constants.ErrorCode;

public class SqQueueException extends Exception{
	
	private static final long serialVersionUID = 7718828512143293558L;
	
	private final ErrorCode code;

	public SqQueueException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqQueueException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqQueueException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqQueueException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}
	
}
