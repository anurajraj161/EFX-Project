package com.task.exception;

public class CRUDControllerException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CustomErrorCode;
	private String CustomErrorMessage;
	public String getCustomErrorCode() {
		return CustomErrorCode;
	}
	public void setCustomErrorCode(String customErrorCode) {
		CustomErrorCode = customErrorCode;
	}
	public String getCustomErrorMessage() {
		return CustomErrorMessage;
	}
	public void setCustomErrorMessage(String customErrorMessage) {
		CustomErrorMessage = customErrorMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public CRUDControllerException(String exception) {
		super(exception);
	}
	public CRUDControllerException(String customErrorCode, String customErrorMessage) {
		super();
		CustomErrorCode = customErrorCode;
		CustomErrorMessage = customErrorMessage;
	}
	

}
