package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetT {
	public static void main(String[] args) {
		String tempp = "/usr/local/svmlin-v1.0/example/japsvm_te_la0_5";// test
		String tempp2 = "/usr/local/svmlin-v1.0/example/data/japsvm_tr_la0";// test
		String prediction = "/usr/workspace/LinSvm/japsvm_tr1_5.outputs";// 预测
		String path1 = "/usr/local/svmlin-v1.0/example/japsvm_te_la1_5";// 真实
		File f1 = new File(path1);
		File f = new File(prediction);// 预测
		List<Integer> orinal = new ArrayList<Integer>();
		List<Integer> pre = new ArrayList<Integer>();
		int X = 0;
		try {
			BufferedReader bu = new BufferedReader(new InputStreamReader(new FileInputStream(f1)));
			for (String line = bu.readLine(); line != null; line = bu.readLine()) {
				orinal.add(Integer.parseInt(line));
			}
			BufferedReader tee = new BufferedReader(new InputStreamReader(new FileInputStream(new File(tempp))));
			for (String line = tee.readLine(); line != null; line = tee.readLine()) {
				if(line.contains("-1")){
					X++;
				}
			}
			System.out.println(X);
			BufferedReader tee2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(tempp2))));
			for (String line = tee2.readLine(); line != null; line = tee2.readLine()) {
				if(line.contains("-1")){
					X++;
				}
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
		System.out.println(X);
		int temp = 0;
		int temp2 = 0;
		for (int i = pre.size() - orinal.size(); i < pre.size(); i++) {
			if (pre.get(i) == 1) {
				temp++;
				if ((orinal.get(i - pre.size() + orinal.size()) == pre.get(i))) {
					temp2++;
				}
			}
		}
		//System.out.println((temp2 + 0.0) / temp);
	}
}
