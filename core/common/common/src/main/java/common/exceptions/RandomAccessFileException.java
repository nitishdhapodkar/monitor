package common.exceptions;

import common.constants.ErrorCode;

public class RandomAccessFileException extends Exception{
	
	private static final long serialVersionUID = 1388383201055515339L;
	private final ErrorCode code;

	public RandomAccessFileException(ErrorCode code) {
		super();
		this.code = code;
	}

	public RandomAccessFileException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public RandomAccessFileException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public RandomAccessFileException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}

}