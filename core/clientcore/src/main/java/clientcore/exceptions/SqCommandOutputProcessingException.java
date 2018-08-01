package clientcore.exceptions;

import common.constants.ErrorCode;

public class SqCommandOutputProcessingException extends Exception{
	
	private static final long serialVersionUID = -4357348051334025470L;
	private final ErrorCode code;

	public SqCommandOutputProcessingException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqCommandOutputProcessingException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqCommandOutputProcessingException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqCommandOutputProcessingException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}
}
