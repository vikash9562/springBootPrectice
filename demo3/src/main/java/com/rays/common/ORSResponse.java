package com.rays.common;

import java.util.HashMap;
import java.util.Map;

public class ORSResponse {
	
	public static final String INPUT_ERROR = "inputError";
	public static final String MESSAGE = "message";
	public static final String DATA = "data";
	
	public  boolean sucess= false;
	
	private Map<String, Object> result = new HashMap<String, Object>();

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public static String getInputError() {
		return INPUT_ERROR;
	}

	public static String getMessage() {
		return MESSAGE;
	}

	public static String getData() {
		return DATA;
	}
	
	public void addInputError(Object value) {
		result.put(INPUT_ERROR, value);
	}
	
	public void addMessage(Object value) {
		result.put(MESSAGE, value);
	}
	
	public void addData(Object value) {
		result.put(DATA, value);
	}
	
	public void addResult(String key, Object value) {
		result.put(key, value);
	}
}
