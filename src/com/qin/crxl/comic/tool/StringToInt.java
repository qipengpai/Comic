package com.qin.crxl.comic.tool;


public class StringToInt {
	
	public static void main(String[] args) {
		System.err.println(toString(" 65 45 22312 20320 24515 37324 57347"));
		System.err.println(toInt("蛋蛋"));
	}
	public static String toInt(String str) {
		String name="";
		char[] one= str.toCharArray();
		for (int i = 0; i < one.length; i++) {
			int os=one[i];
			name=name+" "+os;
		}
		return name;
	}
	
	public static String toString(String str){
		String name="";
		String[] zf=str.split(" ");
		for (int i = 0; i < zf.length; i++) {
			if(!"".equals(zf[i])){
				char zic=(char) Integer.parseInt(zf[i]);
				name =name+zic;
			}
		}
		return name;
	}
}