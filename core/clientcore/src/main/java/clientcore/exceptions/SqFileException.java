package clientcore.exceptions;

public class SqFileException extends Exception{

	private final ErrorCode code;

	public SqFileException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqFileException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqFileException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqFileException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}
}
