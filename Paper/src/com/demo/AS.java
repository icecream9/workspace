package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//无用
public class AS {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		double max = 0;
		String pathout = "/media/root/EC6852336851FD30/Users/zj/Desktop/cre.txt";
		//FileOutputStream fileout = new FileOutputStream(pathout);

		String path3 = "/media/root/EC6852336851FD30/Users/zj/Desktop/res.txt"; // 数据文件地址
		File file3 = new File(path3);
		BufferedReader brr3;
		brr3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
		for (String line = brr3.readLine(); line != null; line = brr3.readLine()) {
			String[] temp = line.split("\t");
			String tq = temp[0];
			for (int i = 1; i < temp.length; ++i) {
				if (Double.valueOf(temp[i]) == 0.0) {
				} else {
					if (i == 2) {
						max=max+Double.valueOf(temp[i]) ;
//						if (Double.valueOf(temp[i]) > max) {
//							//max = Double.valueOf(temp[i]);
//						}
					}

					tq = tq + "\t" + i + ":" + temp[i];
				}
			}
			tq = tq + "\n";
			byte[] arg0 = tq.getBytes();
			//fileout.write(arg0);
		}
System.out.println(max/1);
		brr3.close();
	//	fileout.close();
	}

}
