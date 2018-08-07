package common.exceptions;

import common.constants.ErrorCode;

public class SqFolderException extends Exception{
	
	private static final long serialVersionUID = -8914176281473785123L;
	private final ErrorCode code;

	public SqFolderException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqFolderException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqFolderException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqFolderException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}

}
