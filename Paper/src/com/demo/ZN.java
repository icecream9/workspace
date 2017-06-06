package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZN {

	public static void main(String[] args) throws IOException {
		double min=200000;
		// TODO Auto-generated method stub
		String path1 = "/usr/local/svmlin-v1.0/example/data/cre2"; // 数据文件地址
		FileOutputStream fileout = new FileOutputStream(path1);
		
		String path = "/usr/local/svmlin-v1.0/example/data/cre1"; // 数据文件地址
		File file = new File(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			String[] temp = line.split("\t");
			for (int i = 1; i < temp.length; ++i) {
				if (temp[i].substring(0, 2).contains("2:")) {
					String tq[] = temp[i].split(":");
					temp[i] = "2:" + String.valueOf(Double.parseDouble(tq[1]) / 10);

				} else if (temp[i].substring(0, 2).contains("4:")) {
					String tq[] = temp[i].split(":");
					if(Double.parseDouble(tq[1])<min){
						min=Double.parseDouble(tq[1]);
					}
					temp[i] = "4:" + String.valueOf((Double.parseDouble(tq[1])-197900)/100);
				}else if (temp[i].substring(0, 3).contains("16:")) {
					String tq[] = temp[i].split(":");
					temp[i] = "16:" + String.valueOf(Double.parseDouble(tq[1])-319999);
				}else if (temp[i].substring(0, 3).contains("15:")) {
					String tq[] = temp[i].split(":");
					temp[i] = "15:" + String.valueOf(Double.parseDouble(tq[1])/10);
				}
			}
			String outt=temp[0];
			for(int i=1;i<temp.length;++i){
				outt=outt+"\t"+temp[i];
			}
			outt=outt+"\n";
			byte[] br2 = outt.getBytes();
			fileout.write(br2);
			
			
		}
		System.out.println(min);
		br.close();
		fileout.close();
	}

}
