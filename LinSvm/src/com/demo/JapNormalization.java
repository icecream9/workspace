package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JapNormalization {
	public void japNormalization() {
		int X=0;
		String path = "/media/root/EC6852336851FD30/Users/zj/Desktop/credit/jap.dat";
		File file = new File(path);
		List<String> list = new ArrayList<String>();
		double[] u = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		double[] y = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		// 得到平均值u[i]/690
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				String[] gggg = line.split("\t");
				for (int i = 0; i < 15; i++) {
					if (gggg[i].endsWith("?")) {
					} else {
						u[i] = u[i] + Double.parseDouble(gggg[i]);
					}
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 打得到标准差Math.sqrt(y[i]/690)
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				String[] gggg = line.split("\t");
				for (int i = 0; i < 15; i++) {
					if (gggg[i].endsWith("?")) {
					} else {
						y[i] = y[i] + (Double.parseDouble(gggg[i]) - u[i] / 690)
								* (Double.parseDouble(gggg[i]) - u[i] / 690);
					}
				}
			}

			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 归一化
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				String[] gggg = line.split("\t");
				for (int i = 0; i < 15; i++) {
					if (gggg[i].endsWith("?")) {
						gggg[i] = i + 1 + ":" + (-u[i] / y[i]);
					} else {
						gggg[i] = i + 1 + ":" + ((Double.parseDouble(gggg[i]) - u[i] / 690) / Math.sqrt(y[i] / 690));
					}
				}

				if (Double.parseDouble(gggg[15]) != 1) {
					gggg[15] = "-1";
				} else {
					gggg[15] = "1";
				}

				String temp = gggg[15];
				for (int j = 0; j < 15; j++) {
					temp = temp + "\t" + gggg[j];
				}
				temp = temp + "\n";
				list.add(temp);
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {

			FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/data/japsvm_train");
			for (int k = 0; k < list.size(); k++) {
				byte[] br1 = list.get(k).getBytes();
				fileout.write(br1);
			}
			fileout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	 public static void main(String[]args){
//	 new JapNormalization().japNormalization();
//	 }
}
