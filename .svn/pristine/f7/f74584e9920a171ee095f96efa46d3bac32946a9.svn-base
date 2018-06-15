package com.qin.crxl.comic.qny;

import java.io.File;

public class FileM {

	public static boolean deleteFile(String fileName) {  
		  File file = new File(fileName);  
		  // ����ļ�·�����Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��  
		  if (file.exists() && file.isFile()) {  
		   if (file.delete()) {  
		    System.out.println("ɾ����ļ�" + fileName + "�ɹ���");  
		    return true;  
		   } else {  
		    System.out.println("ɾ����ļ�" + fileName + "ʧ�ܣ�");  
		    return false;  
		   }  
		  } else {  
		   System.out.println("ɾ����ļ�ʧ�ܣ�" + fileName + "�����ڣ�");  
		   return false;  
		  }  
		 }  
}
