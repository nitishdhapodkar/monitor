package common.util.mongo;

import common.constants.ErrorCode;

public class SqDatabaseConnectionException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private final ErrorCode code;

	public SqDatabaseConnectionException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqDatabaseConnectionException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqDatabaseConnectionException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqDatabaseConnectionException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}
}