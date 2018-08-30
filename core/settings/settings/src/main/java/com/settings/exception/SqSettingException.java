package com.settings.exception;

import com.constants.ErrorCode;

public class SqSettingException  extends Exception{
	
	private static final long serialVersionUID = 2065083661398193513L;
	private final ErrorCode code;

	public SqSettingException(ErrorCode code) {
		super();
		this.code = code;
	}

	public SqSettingException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}

	public SqSettingException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}

	public SqSettingException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return this.code;
	}

}
