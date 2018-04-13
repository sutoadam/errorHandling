package com.interticket.errorhandling.core;

public class ITFunctionException extends Exception {

	private Exception storedException = null;

	public ITFunctionException(Exception storedException) {
		this.storedException = storedException;
	}

	public ITFunctionException(String message, Exception storedException) {
		super(message);
		this.storedException = storedException;
	}

	public ITFunctionException(String message, Throwable cause, Exception storedException) {
		super(message, cause);
		this.storedException = storedException;
	}

	public ITFunctionException(Throwable cause, Exception storedException) {
		super(cause);
		this.storedException = storedException;
	}

	public ITFunctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Exception storedException) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.storedException = storedException;
	}

	public Exception getStoredException() {
		return storedException;
	}
}
