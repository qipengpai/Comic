package com.qin.crxl.comic.exception;

public class ErrorBase {

	private String code;

	public ErrorBase(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code.toString();
	}

}
