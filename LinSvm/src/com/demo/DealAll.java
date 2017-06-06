package com.demo;

import java.io.File;

public class DealAll {
//	public static void main(String[] args) {
//		 String path = "/usr/local/svmlin-v1.0/example";
//		dealAll(path);
//
//	}

	public boolean dealAll(String path) {
		// String path = "/usr/local/svmlin-v1.0/example";
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		if (!file.isDirectory()) {
			return false;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				dealAll(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				// delFolder(path + "/" + tempList[i]);//再删除空文件夹
			}		
		}
		return true;
	}
}
