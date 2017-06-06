package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZS21 {

	public static void main(String[] args) throws IOException {
		String path = "/usr/local/svmlin-v1.0/example/data/aus.txt"; // 数据文件地址
		FileOutputStream fileout = new FileOutputStream(path);
		String path3 = "/media/root/EC6852336851FD30/Users/zj/Desktop/atala/australian.dat"; // 数据文件地址
		File file3 = new File(path3);
		BufferedReader brr3;
		brr3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
		for (String line = brr3.readLine(); line != null; line = brr3.readLine()) {
			String[] temp = line.split(" ");
			String tt;
			if (temp[14].contains("0")) {
				tt = "-1";
			} else {
				tt = "1";
			}
			// System.out.println(temp.length);
			for (int i = 0; i < 14; ++i) {
				if (!temp[i].equals("0")) {
					tt = tt + "\t" + (i+1)+":"+temp[i];
				}

			}
			tt = tt + "\n";
			byte[] br1 = tt.getBytes();
			fileout.write(br1);

		}
		brr3.close();
		fileout.close();
	}

}
