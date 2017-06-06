package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FF {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String path = "/media/root/EC6852336851FD30/Users/zj/Desktop/out.txt"; // 数据文件地址
		File file = new File(path);
		BufferedReader br;
		String pathlable = "/media/root/EC6852336851FD30/Users/zj/Desktop/label.txt";
		File file2 = new File(pathlable);
		BufferedReader br2;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
		String path1 = "/media/root/EC6852336851FD30/Users/zj/Desktop/out1.txt"; // 数据文件地址
		File file1 = new File(path1);
		BufferedReader brr1;
		brr1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));

		String path3 = "/media/root/EC6852336851FD30/Users/zj/Desktop/out2.txt"; // 数据文件地址
		File file3 = new File(path3);
		BufferedReader brr3;
		brr3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));

		String pathout = "/media/root/EC6852336851FD30/Users/zj/Desktop/res.txt";
		FileOutputStream fileout = new FileOutputStream(pathout);

		String pathout1 = "/media/root/EC6852336851FD30/Users/zj/Desktop/res1.txt";
		FileOutputStream fileout1 = new FileOutputStream(pathout1);

		String line = null;
		String temp = null;
		while ((line = br.readLine()) != null) {

			if (line.contains("-") & line.contains("32")) {
				temp = br2.readLine();
				line = br.readLine();
			} else if (!line.contains("-")) {
				temp = br2.readLine();
				line = temp + "\t" + line + "\n";
				byte[] br1 = line.getBytes();
				fileout.write(br1);
			}
		}

		while ((line = brr1.readLine()) != null) {

			if (line.contains("-") & line.contains("32")) {
				temp = br2.readLine();
				line = brr1.readLine();
			} else if (!line.contains("-")) {
				temp = br2.readLine();
				line = temp + "\t" + line + "\n";
				byte[] br1 = line.getBytes();
				fileout.write(br1);
			}
		}

		while ((line = brr3.readLine()) != null) {

			if (line.contains("-") & line.contains("32")) {
				line = brr3.readLine();
				temp = br2.readLine();
			} else if (!line.contains("-")) {
				temp = br2.readLine();
				line = temp + "\t" + line + "\n";
				byte[] br1 = line.getBytes();
				fileout1.write(br1);
			}
		}

		br.close();
		br2.close();
		brr1.close();
		brr3.close();
		fileout.close();
		fileout1.close();
	}

}
