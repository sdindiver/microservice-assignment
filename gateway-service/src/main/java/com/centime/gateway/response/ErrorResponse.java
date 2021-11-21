package com.centime.gateway.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "errorCode", "errorMessage" })
public class ErrorResponse {

	private Integer errorCode;
	private String errorMessage;

	/**
	 * @param dsStatus
	 */
	public ErrorResponse(int errorCode, String errorMsg) {
		super();
		this.setErrorCode(errorCode);
		this.setErrorMessage(errorMsg);
	}

	public ErrorResponse() {
	}

	/**
	 * 
	 * @return
	 */

	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 
	 * 
	 * @param errorMessage
	 */

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 
	 * @return
	 */

	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * 
	 * 
	 * @param errorCode
	 */

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "{\"errorCode\": \"" + errorCode + "\", \"errorMessage\": \"" + errorMessage + "\"}";
	}
}
