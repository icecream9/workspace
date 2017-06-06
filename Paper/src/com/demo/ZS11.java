package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZS11 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String pathout = "/media/root/EC6852336851FD30/Users/zj/Desktop/atala/german.txt";
		FileOutputStream fileout = new FileOutputStream(pathout);
		
		String path3 = "/media/root/EC6852336851FD30/Users/zj/Desktop/credit/german_num.dat"; // 数据文件地址
		File file3 = new File(path3);
		BufferedReader brr3;
		brr3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
		int k=0;
		for (String line = brr3.readLine(); line != null; line = brr3.readLine()) {
			String[] temp = line.split("	");
			String tt=temp[0];
			for(int i=1;i<25;++i){
				tt=tt+" "+temp[i];
			}
			tt=tt+"\n";
			//System.out.println(temp[temp.length-2]);
			byte[] br1 = tt.getBytes();
			fileout.write(br1);
//			System.out.println(temp.length);
		}
		System.out.println(k);
		brr3.close();
		fileout.close();
	}

}
