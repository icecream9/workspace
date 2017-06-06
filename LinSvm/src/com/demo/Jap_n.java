package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jap_n {

	public void Randd(int xx){
		
		int x =xx;
		int yy = 690;

		RandomX r = new RandomX();
		int[] array = r.getRandomX(x, yy);
//		for(int a=0;a<array.length;a++){
//			System.out.println(array[a]);
//		}
		Arrays.sort(array);
		String path = "/media/root/EC6852336851FD30/Users/zj/Desktop/credit/jap.dat";
		File file = new File(path);
		// double xx=Double.parseDouble(".33");
		// System.out.println(xx);
		List<String> list = new ArrayList<String>();
		List<String> list_test = new ArrayList<String>();
		List<String> lable = new ArrayList<String>();
		List<String> lable_test = new ArrayList<String>();
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
			int q = 0;
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

				String temp = gggg[0];
				for (int j = 1; j < 15; j++) {
					temp = temp + "\t" + gggg[j];
				}
				temp = temp + "\n";
				// System.out.println(temp);

//				if (Arrays.binarySearch(array, q) < 0) {
//					list_test.add(temp);
//					lable.add("0" + "\n");
//					lable_test.add(gggg[15]+"\n");
//				} else {
//					lable.add(gggg[15]+"\n");
//				}
				if(Arrays.binarySearch(array, q) >=0){
					list.add(temp);
					lable.add(gggg[15] + "\n");
				}else{
					list_test.add(temp);
					lable_test.add(gggg[15]+"\n");
				}
				list.add(temp);
				q++;
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int k=0;k<list_test.size();k++){
			list.add(list_test.get(k));
			lable.add("0" + "\n");
		}

		// trian
		try {
//			FileOutputStream fileout = new FileOutputStream(
//					"/media/root/EC6852336851FD30/Users/zj/Desktop/credit/japsvm_train"+x+".txt");
			FileOutputStream fileout = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/japsvm_train"+x+".txt");
			for (int k = 0; k < list.size(); k++) {
				byte[] br1 = list.get(k).getBytes();
				fileout.write(br1);
			}

			fileout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// train_lable
		try {
//			FileOutputStream fileout = new FileOutputStream(
//					"/media/root/EC6852336851FD30/Users/zj/Desktop/credit/japsvm_tr_lable"+x+".txt");
			FileOutputStream fileout = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/japsvm_tr_lable"+x+".txt");			
			for (int k = 0; k < lable.size(); k++) {
				byte[] br1 =  lable.get(k).getBytes();
				fileout.write(br1);
			}

			fileout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// test
		try {
//			FileOutputStream fileout = new FileOutputStream(
//					"/media/root/EC6852336851FD30/Users/zj/Desktop/credit/japsvm_test"+x+".txt");
			FileOutputStream fileout = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/japsvm_test"+x+".txt");
			for (int k = 0; k < list_test.size(); k++) {
				byte[] br1 = list_test.get(k).getBytes();
				fileout.write(br1);
			}

			fileout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// test_lable
		try {
//			FileOutputStream fileout = new FileOutputStream(
//					"/media/root/EC6852336851FD30/Users/zj/Desktop/credit/japsvm_te_lable"+x+".txt");
			FileOutputStream fileout = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/japsvm_te_lable"+x+".txt");
			for (int k = 0; k <lable_test.size(); k++) {
				byte[] br1 =lable_test.get(k).getBytes();
				fileout.write(br1);
			}

			fileout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	//Randd(138);
	}

}
