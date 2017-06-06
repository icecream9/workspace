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
import java.util.List;

public class KFlod {
	public void kFlod(int k1, int total1) {
		int k = k1;
		int total = total1;
		int num = total - total / k * k;
		int used = 0;
		String path = "/usr/local/svmlin-v1.0/example/data/japsvm_train";
		File file = new File(path);
		List<String> list = new ArrayList<String>();
		List<String> lable = new ArrayList<String>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				String[] gggg = line.split("\t");
				String ati = gggg[1];
				for (int i = 2; i < 16; i++) {
					ati = ati + "\t" + gggg[i];
				}
				lable.add(gggg[0] + "\n");
				list.add(ati + "\n");
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < k; i++) {
			int y = total / k;

			if (i < num) {
				y = y + 1;
			}
			int[] array = RandomX.getRandomX(y, total - used);
			Arrays.sort(array);
			// System.out.println(i);
			try {
				FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/data/japsvm_tr" + i);
				FileOutputStream fileout2 = new FileOutputStream(
						"/usr/local/svmlin-v1.0/example/data/japsvm_tr_la" + i);
				for (int j = array.length; j > 0; j--) {

					byte[] br1 = list.get(array[j - 1]).getBytes();
					fileout.write(br1);

					list.remove(j - 1);
					byte[] br2 = lable.get(array[j - 1]).getBytes();
					fileout2.write(br2);
					lable.remove(j - 1);
				}
				fileout.close();
				fileout2.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			used = used + y;
		}

	}

	public void kFlodaus(int k, int total) {
		int num = total - total / k * k;
		int used = 0;
		String path = "/usr/local/svmlin-v1.0/example/data/aussvm_train";
		File file = new File(path);
		List<String> list = new ArrayList<String>();
		List<String> lable = new ArrayList<String>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				String[] gggg = line.split("\t");
				String ati = gggg[1];
				for (int i = 2; i < 15; i++) {
					ati = ati + "\t" + gggg[i];
				}
				lable.add(gggg[0] + "\n");
				list.add(ati + "\n");
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < k; i++) {
			int y = total / k;

			if (i < num) {
				y = y + 1;
			}
			int[] array = RandomX.getRandomX(y, total - used);
			Arrays.sort(array);
			// System.out.println(i);
			try {
				FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/data/aussvm_tr" + i);
				FileOutputStream fileout2 = new FileOutputStream(
						"/usr/local/svmlin-v1.0/example/data/aussvm_tr_la" + i);
				for (int j = array.length; j > 0; j--) {

					byte[] br1 = list.get(array[j - 1]).getBytes();
					fileout.write(br1);

					list.remove(j - 1);
					byte[] br2 = lable.get(array[j - 1]).getBytes();
					fileout2.write(br2);
					lable.remove(j - 1);
				}
				fileout.close();
				fileout2.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			used = used + y;
		}
	}

	public void kFlodger(int k, int total) {
		int num = total - total / k * k;
		int used = 0;
		String path = "/usr/local/svmlin-v1.0/example/data/gersvm_train";
		File file = new File(path);
		List<String> list = new ArrayList<String>();
		List<String> lable = new ArrayList<String>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				String[] gggg = line.split("\t");
				String ati = gggg[1];
				for (int i = 2; i < 25; i++) {
					ati = ati + "\t" + gggg[i];
				}
				lable.add(gggg[0] + "\n");
				list.add(ati + "\n");
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < k; i++) {
			int y = total / k;

			if (i < num) {
				y = y + 1;
			}
			int[] array = RandomX.getRandomX(y, total - used);
			Arrays.sort(array);
			// System.out.println(i);
			try {
				FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/data/gersvm_tr" + i);
				FileOutputStream fileout2 = new FileOutputStream(
						"/usr/local/svmlin-v1.0/example/data/gersvm_tr_la" + i);
				for (int j = array.length; j > 0; j--) {

					byte[] br1 = list.get(array[j - 1]).getBytes();
					fileout.write(br1);

					list.remove(j - 1);
					byte[] br2 = lable.get(array[j - 1]).getBytes();
					fileout2.write(br2);
					lable.remove(j - 1);
				}
				fileout.close();
				fileout2.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			used = used + y;
		}
	}

}
