package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//无用
public class SS {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		String pathout = "/media/root/EC6852336851FD30/Users/zj/Desktop/res.txt";
		FileOutputStream fileout = new FileOutputStream(pathout,true);
		String path3 = "/media/root/EC6852336851FD30/Users/zj/Desktop/res1.txt"; // 数据文件地址
		File file3 = new File(path3);
		BufferedReader brr3;
		brr3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
		for (String line = brr3.readLine(); line != null; line = brr3.readLine()) {
			String [] temp=line.split("\t");
			String tq=temp[0];
			for(int i=2;i<temp.length;++i){
				tq=tq+"\t"+temp[i];
			}
			tq=tq+"\n";
			byte[] br1 =tq.getBytes();
			fileout.write(br1);
		}
		
		brr3.close();
		fileout.close();
	}
}
