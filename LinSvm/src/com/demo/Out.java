package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Out {
	public void jap(int k,int t,String path) {
		Out1(k,t,path);
	}
	
	public void ger(int k,int t,String path) {
		Out1(k,t,path);
	}
	
	public void aus(int k,int t,String path) {
		Out1(k,t,path);
	}

	private void Out1(int k, int t,String path) {
		double ave = ave(k);
		double pre=precision(k);
		double time = 0;
		if (t == 1) {
			time = avetime(k);
			//String path = "/usr/local/svmlin-v1.0/out/avejap";
			try {
				FileOutputStream fileout = new FileOutputStream(path, true);
				String temp = "The " + k + " TSVm " + " ave:" + ave + "," + " time:" + time +", "+" precision:"+pre+"\n";
				byte[] br1 = temp.getBytes();
				fileout.write(br1);
				fileout.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			time = avetimeD(k);
			//String path = "/usr/local/svmlin-v1.0/out/avejap";
			try {
				FileOutputStream fileout = new FileOutputStream(path, true);
				String temp = "The " + k + " DASVm " + "ave:" + ave + "," + " time:" + time +", "+" precision:"+pre+ "\n";
				byte[] br1 = temp.getBytes();
				fileout.write(br1);
				fileout.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private double precision(int k) {
		String path = "/usr/local/svmlin-v1.0/out/preci" + k;
		File file = new File(path);
		double average = 0;
		int yq = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(Double.parseDouble(line));
				average = average + Double.parseDouble(line);
				yq++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File f = new File(path);
		if (f.exists())
			f.delete();

		System.out.println(average / yq);
		return average / yq;
	}

	private double ave(int k) {
		String path = "/usr/local/svmlin-v1.0/out/log" + k;
		File file = new File(path);
		double average = 0;
		int yq = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(Double.parseDouble(line));
				average = average + Double.parseDouble(line);
				yq++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File f = new File(path);
		if (f.exists())
			f.delete();

		System.out.println(average / yq);
		return average / yq;
	}

	private double avetime(int k) {
		String path = "/usr/local/svmlin-v1.0/out/time" + k;

		File file = new File(path);
		double average = 0;
		int yq = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(Double.parseDouble(line));
				average = average + Double.parseDouble(line);
				yq++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File(path);
		if (f.exists())
			f.delete();

		System.out.println(average / yq);
		return average / yq;
	}

	private double avetimeD(int k) {
		String path1 = "/usr/local/svmlin-v1.0/out/timeDA" + k;
		File file = new File(path1);
		double average = 0;
		int yq = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(Double.parseDouble(line));
				average = average + Double.parseDouble(line);
				yq++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File f = new File(path1);
		if (f.exists())
			f.delete();

		System.out.println(average / yq);
		return average / yq;
	}

}
