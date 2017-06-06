package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZDS {

	public static void main(String[] args) throws IOException {
		String path = "/usr/local/svmlin-v1.0/example/data/jap.txt"; // 数据文件地址
		FileOutputStream fileout = new FileOutputStream(path);
		String path3 = "/media/root/EC6852336851FD30/Users/zj/Desktop/atala/japsvm.txt"; // 数据文件地址
		File file3 = new File(path3);
		String path4 = "/media/root/EC6852336851FD30/Users/zj/Desktop/atala/japsvm_lab.txt"; // 数据文件地址
		File file4 = new File(path4);
		BufferedReader brr4;
		BufferedReader brr3;
		brr3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
		brr4 = new BufferedReader(new InputStreamReader(new FileInputStream(file4)));
		for (String line = brr4.readLine(); line != null; line = brr4.readLine()) {
			line = line + "\t" + brr3.readLine()+"\n";
			byte[] tt=line.getBytes();
			fileout.write(tt);
		}
		brr3.close();
		brr4.close();
		fileout.close();
	}

}
