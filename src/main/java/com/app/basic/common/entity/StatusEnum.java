package com.app.basic.common.entity;

public enum StatusEnum {

	OK(200, "OK"),
	BAD_REQUEST(400, "BAD_REQUEST"),
	UNAUTHORIZED(401, "UNAUTHORIZED"),
	NOT_FOUND(404, "NOT_FOUND"),
	FORBIDDEN(403, "FORBIDDEN"),
	INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

	int code;
	String message;

	StatusEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}
}
