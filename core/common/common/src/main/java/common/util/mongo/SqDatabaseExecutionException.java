package common.util.mongo;

import common.constants.ErrorCode;

public class SqDatabaseExecutionException extends Exception{
	 
	private static final long serialVersionUID = 1L;
	private final ErrorCode code;

	public SqDatabaseExecutionException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqDatabaseExecutionException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqDatabaseExecutionException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqDatabaseExecutionException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}
	
}