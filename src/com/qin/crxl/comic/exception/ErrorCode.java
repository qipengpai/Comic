package com.qin.crxl.comic.exception;

public class ErrorCode extends ErrorBase {



	public ErrorCode(String code) {
		super(code);
	}
	public static final String s_ok="200-操作成功";
	public static final String NOT_RESOURCE="404-找不到资源";
	public static final String LOGIN_ERROR="405-用户名或者密码错误";
	public static final String VCODE_ERROR="406-验证码错误";
	public static final String NOT_AUTHORIZATION="407-非授权用户";
	
}
