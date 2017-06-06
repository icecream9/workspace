package com.demo;

//
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Jap {
	public static void main(String[] args) {
		String path = "/media/root/EC6852336851FD30/Users/zj/Desktop/credit/jap.dat";
		File file = new File(path);
		// double xx=Double.parseDouble(".33");
		// System.out.println(xx);
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				String[] gggg = line.split("\t");
				int k = 0;
				for (int i = 0; i < 15; i++) {
					if (gggg[i].endsWith("?")) {
					} else if (Double.parseDouble(gggg[i]) != 0.0) {
						gggg[k] = i + 1 + ":" + ((Double.parseDouble(gggg[i])));
						k++;
					}
				}
				String temp = gggg[0];
				// String temp = gggg[15];
				for (int j = 1; j < k; j++) {
					temp = temp + "\t" + gggg[j];
				}
				temp = temp + "\n";
				// System.out.println(temp);
				list.add(temp);
				if (Double.parseDouble(gggg[15]) != 1) {
					gggg[15] = "-1";
				} else {
					gggg[15] = "1";
				}
				list2.add(gggg[15]+"\n");

			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			FileOutputStream fileout = new FileOutputStream(
					"/media/root/EC6852336851FD30/Users/zj/Desktop/credit/japsvm.txt");
			for (int k = 0; k < list.size(); k++) {
				byte[] br1 = list.get(k).getBytes();
				fileout.write(br1);
			}

			fileout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			FileOutputStream fileout = new FileOutputStream(
					"/media/root/EC6852336851FD30/Users/zj/Desktop/credit/japsvm_lab.txt");
			for (int k = 0; k < list2.size(); k++) {
				byte[] br1 = list2.get(k).getBytes();
				fileout.write(br1);
			}

			fileout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
