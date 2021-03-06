package serverCore.exception;

import common.constants.ErrorCode;

public class LicenceException extends Exception{
	
	private static final long serialVersionUID = 1192153463976168170L;
	private final ErrorCode code;

	public LicenceException(ErrorCode code) {
		super();
		this.code = code;
	}

	public LicenceException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public LicenceException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public LicenceException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}

}