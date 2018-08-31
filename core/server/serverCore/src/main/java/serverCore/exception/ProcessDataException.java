package serverCore.exception;

import common.constants.ErrorCode;

public class ProcessDataException  extends Exception{
	
	private static final long serialVersionUID = 1L;
	private final ErrorCode code;

	public ProcessDataException(ErrorCode code) {
		super();
		this.code = code;
	}

	public ProcessDataException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public ProcessDataException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public ProcessDataException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}

}