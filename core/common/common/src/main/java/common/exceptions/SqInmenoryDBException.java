package common.exceptions;

import common.constants.ErrorCode;

public class SqInmenoryDBException extends Exception{
	
	private static final long serialVersionUID = 5025825078963195072L;
	private final ErrorCode code;

	public SqInmenoryDBException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqInmenoryDBException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqInmenoryDBException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqInmenoryDBException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}
	
}