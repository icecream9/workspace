package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KFlod {
	public static void main(String[] args) throws IOException {
		//new KFlod().kFlod(1, 15757);
	}

	public void kFlod(int k, int total) throws IOException {
		int row = 0;

		int flod[][] = RandomX_Y.getRandomX(k, total);
		for (int kt = 0; kt < k; ++kt) {
			Arrays.sort(flod[kt]);
		}
		String path = "/usr/local/svmlin-v1.0/example/data/t/cre37.txt"; // 数据文件地址
		// String path2 = "/usr/local/svmlin-v1.0/example/data/label.txt"; //
		// 数据文件地址
		File file = new File(path);
		// File file2 = new File(path2);

		Set<Integer> set = new HashSet<Integer>();

		Set<String>[] sett = new HashSet[k];
		for (int i = 0; i < sett.length; ++i) {
			sett[i] = new HashSet<>();
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				for (int i = 0; i < k; ++i) {
					if (Arrays.binarySearch(flod[i], row) >= 0) {
						sett[i].add(line + "\n");

						break;
					}
				}
				row++;
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < k; ++i) {
			String pathout = "/usr/local/svmlin-v1.0/example/data/test" + i;
			String pathout2 = "/usr/local/svmlin-v1.0/example/data/label" + i;

			FileOutputStream fileout = new FileOutputStream(pathout);
			FileOutputStream fileout2 = new FileOutputStream(pathout2);
			for (String tq : sett[i]) {
				String[] temp = tq.split("\t");
				String out2 = temp[0] + "\n";
				byte[] br2 = out2.getBytes();
				fileout2.write(br2);
				String out1 = temp[1];
				for (int j = 2; j < temp.length; ++j) {
					out1 = out1 + "\t" + temp[j];
				}
				// out1=out1+"\n";
				byte[] br1 = out1.getBytes();
				fileout.write(br1);

			}
			fileout.close();
			fileout2.close();
		}

	}



}
