package com.qin.crxl.comic.tool;

import java.io.File;
import java.io.IOException;

public class StaticPar {
	
	/**
	 * 站内资源根目录
	 */
//	private static final String forobjpath="F:\\ gongju\\.metadata\\.me_tcat7\\webapps\\";
//	private static final String head_img="DOG\\IMG\\HEAD\\";
	private static final String forobjpath="f:\\testimg\\";
	private static final String head_img="/DOG/IMG/HEAD/";
	private static final String http_head_img="DOG/IMG/HEAD/";
	
	
	  public static String getForobjpath() {
		  judeFileExists(new File(forobjpath));
		  judeFileExists(new File(forobjpath+head_img));
		return forobjpath;
	}


	public static String getHeadImg() {
		 
		return head_img;
	}


	public static String getHttpHeadImg() {
		return http_head_img;
	}


	// 判断文件是否存在
    public static void judeFileExists(File file) {

    	 if (file.exists()) {
             if (file.isDirectory()) {
                 System.out.println("dir exists");
             } else {
                 System.out.println("the same name file exists, can not create dir");
             }
         } else {
             System.out.println("dir not exists, create it ...");
             file.mkdir();
         }


    }
}
