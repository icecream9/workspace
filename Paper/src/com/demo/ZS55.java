package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ZS55 {
	public static void main(String[] args) throws IOException {
		int x = 0;
		int y = 0;
		Set<Integer> set = new HashSet<Integer>();

		String pathout = "/usr/local/svmlin-v1.0/example/data/t/cre46.txt";
		FileOutputStream fileout = new FileOutputStream(pathout);

		String path3 = "/usr/local/svmlin-v1.0/example/data/t/cre73.txt"; // 数据文件地址
		File file3 = new File(path3);
		BufferedReader brr3;
		brr3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
		for (String line = brr3.readLine(); line != null; line = brr3.readLine()) {
			
			String[] temp = line.split("\t");
			String tq = temp[0];
			x++;
			String tempp = line + "\n";

			if (tq.contains("-1")) {
				byte[] br1 = tempp.getBytes();
				fileout.write(br1);
				// set.add(x);
				++y;
			} else if (x %5 == 0) {
				byte[] br1 = tempp.getBytes();
				fileout.write(br1);
				++y;
			}else if (x % 8 == 0) {
				byte[] br1 = tempp.getBytes();
				fileout.write(br1);
				++y;
			}
		}
		brr3.close();
		fileout.close();
		System.out.println(y);
		System.out.println(1338.0/y);
		// for (int a : set) {
		// System.out.println(a);
		// }
	}
}
