package com.interticket.errorhandling.core.model;

/**
 * Created by User on 2018. 02. 09..
 */
public class ITErrorMessages {
	private String label;
	private String message;

	public ITErrorMessages() {
	}

	public ITErrorMessages(String label, String message) {
		this.label = label;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ITErrorMessages{" +
				"label='" + label + '\'' +
				", message='" + message + '\'' +
				'}';
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
