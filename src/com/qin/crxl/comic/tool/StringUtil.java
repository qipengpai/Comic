package com.qin.crxl.comic.tool;

public class StringUtil {

	/**
	 * 
	 * <h1>判断一个字符串是否为null</h1>
	 * <p>为空=true</p>
	 * <p>不为空=false</p>
	 * @param str
	 * @return
	 */
	public static final boolean bool(String str){
		if(str ==null){
			return true;
		}
		if("".equals(str)){
			return true;
		}
		if(""==str){
			return true;
		}
		return false;
	}
}
