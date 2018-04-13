package com.interticket.errorhandling.core.model;

import java.util.Collection;

/**
 * Created by User on 2018. 01. 25..
 */
public class ITErrorObject {
	private String errorCode;
	private String logLevel;
	private Collection<ITErrorMessages> messages;

	public ITErrorObject() {
	}

	public ITErrorObject(String errorCode, String logLevel, Collection<ITErrorMessages> messages) {
		this.errorCode = errorCode;
		this.logLevel = logLevel;
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "ITErrorObject{" +
				", errorCode=" + errorCode +
				", logLevel='" + logLevel + '\'' +
				", messages=" + messages +
				'}';
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public Collection<ITErrorMessages> getMessages() {
		return messages;
	}

	public void setMessages(Collection<ITErrorMessages> messages) {
		this.messages = messages;
	}
}