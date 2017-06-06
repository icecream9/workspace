package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetPrecision {
	public void getPrecision(String key1,String key2,int k) {
		
		String prediction = "/usr/workspace/LinSvm/"+key1;// 预测
		String path1 = "/usr/local/svmlin-v1.0/example/"+key2;// 真实
		File f1 = new File(path1);
		File f = new File(prediction);// 预测
		List<Integer> orinal = new ArrayList<Integer>();
		List<Integer> pre = new ArrayList<Integer>();

		try {
			BufferedReader bu = new BufferedReader(new InputStreamReader(new FileInputStream(f1)));
			for (String line = bu.readLine(); line != null; line = bu.readLine()) {
				orinal.add(Integer.parseInt(line));
			}

			BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(f)));// 预测
			for (String line = buff.readLine(); line != null; line = buff.readLine()) {
				if (Double.parseDouble(line) > 0) {
					pre.add(1);
				} else {
					pre.add(-1);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int temp = 0;
		int temp2=0;
		for (int i = pre.size() - orinal.size(); i < pre.size(); i++) {
			if (pre.get(i)==1) {
				temp++;
				if((orinal.get(i - pre.size() + orinal.size()) == pre.get(i))){
					temp2++;
				}
			}
		}	
		String path="/usr/local/svmlin-v1.0/out/preci" + k;
		try {
			FileOutputStream fileout = new FileOutputStream(path, true);
			byte[] br =String.valueOf((temp2 +0.0) / temp).getBytes();
			fileout.write (br);
			byte[] br2 = "\n".getBytes();
			fileout.write(br2);
			fileout.flush();
			fileout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println((temp2 +0.0) / temp);
		
	}
}
