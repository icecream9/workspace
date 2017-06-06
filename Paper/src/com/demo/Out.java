package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Out {
	public void jap(int k, int t, String path) {
		Out1(k, t, path);
	}

	public void ger(int k, int t, String path) {
		Out1(k, t, path);
	}

	public void aus(int k, int t, String path) {
		Out1(k, t, path);
	}

	private void Out1(int k, int t, String path) {
		double ave = ave(k, t);
		double pre = precision(k, t);
		double time = 0;
		double rec = recall(k, t);
		double f1measure = 2 * pre * rec / (pre + rec);
		if (t == 2) {
			time = avetime2(k);
			// String path = "/usr/local/svmlin-v1.0/out/avejap";
			try {
				FileOutputStream fileout = new FileOutputStream(path, true);
				String temp = "The " + k + " TSVm " + " ave:" + ave + "," + " time:" + time + ", " + " precision:" + pre
						+ ",recall:" + rec + ",f-1measure:" + f1measure + "\n";
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

		} else if (t == 0) {
			time = avetime(k);
			// String path = "/usr/local/svmlin-v1.0/out/avejap";
			try {
				FileOutputStream fileout = new FileOutputStream(path, true);
				String temp = "The " + k + " RLS " + " ave:" + ave + "," + " time:" + time + ", " + " precision:" + pre
						+ ",recall:" + rec + ",f-1measure:" + f1measure + "\n";
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
		} else if (t == 1) {
			time = avetime1(k);
			// String path = "/usr/local/svmlin-v1.0/out/avejap";
			try {
				FileOutputStream fileout = new FileOutputStream(path, true);
				String temp = "The " + k + " l2SVm " + " ave:" + ave + "," + " time:" + time + ", " + " precision:"
						+ pre + ",recall:" + rec + ",f-1measure:" + f1measure + "\n";
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

		} else if (t == 3) {
			time = avetimeD(k);
			// String path = "/usr/local/svmlin-v1.0/out/avejap";
			try {
				FileOutputStream fileout = new FileOutputStream(path, true);
				String temp = "The " + k + " DASVm " + "ave:" + ave + "," + " time:" + time + ", " + " precision:" + pre
						+ ",recall:" + rec + ",f-1measure:" + f1measure + "\n";
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

	private double recall(int k, int t) {
		String path = "/usr/local/svmlin-v1.0/out/rec" + t + k;
		File file = new File(path);
		double average = 0;
		int yq = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				if (line.contains("NaN")) {
					
					yq++;
				}else{
					average = average + Double.parseDouble(line);
					yq++;
				}

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

	private double precision(int k, int t) {
		String path = "/usr/local/svmlin-v1.0/out/preci" + t + k;
		File file = new File(path);
		double average = 0;
		int yq = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				if (line.contains("NaN") ) {
					
					yq++;
				}else{
					average = average + Double.parseDouble(line);
					yq++;
				}
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

	private double ave(int k, int t) {
		String path = "/usr/local/svmlin-v1.0/out/log" + t + k;
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
		String path = "/usr/local/svmlin-v1.0/out/time/time" + k;

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

	private double avetime1(int k) {
		String path = "/usr/local/svmlin-v1.0/out/time1/time" + k;

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

	private double avetime2(int k) {
		String path = "/usr/local/svmlin-v1.0/out/time2/time" + k;

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
		String path1 = "/usr/local/svmlin-v1.0/out/time3/timeDA" + k;
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
