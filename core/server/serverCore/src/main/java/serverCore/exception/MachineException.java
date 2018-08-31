package serverCore.exception;

import common.constants.ErrorCode;

public class MachineException extends Exception{
	
	private static final long serialVersionUID = -5726271899381109006L;
	private final ErrorCode code;

	public MachineException(ErrorCode code) {
		super();
		this.code = code;
	}

	public MachineException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public MachineException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public MachineException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}

}
