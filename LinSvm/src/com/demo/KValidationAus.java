package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KValidationAus {

	public void kValidation(int k, int t) {
		for (int i = 0; i < k; i++) {
			Date date = new Date();
			System.out.println(i + "ci" + date);
			System.out.println(i + "ci" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
			pretest(i, k);
			test(i, k, t);
			String key1="aussvm_tr"+i+"_"+k+".outputs";
			String key2="aussvm_te_la"+i+"_"+k;
			new GetPrecision().getPrecision(key1, key2,k);
		}
		new Out().aus(k,t,"/usr/local/svmlin-v1.0/out/aveaus");
	}

	private void test(int i, int k, int t) {
		Runtime runt0 = Runtime.getRuntime();
		String f1 = "/usr/local/svmlin-v1.0/example/aussvm_tr" + i + "_" + k;
		String f2 = "/usr/local/svmlin-v1.0/example/aussvm_tr_la" + i + "_" + k;
		if (t == 1) {
			String[] comman0 = { "/usr/local/svmlin-v1.0/svmlin", "-A", "2", "-W", "0.001", "-U", "1", "-R", "0.43", f1,
					f2 };
			Process process1;
			try {
				process1 = runt0.exec(comman0);
				process1.getInputStream();
				InputStream stderr1 = process1.getInputStream();
				InputStreamReader isr1 = new InputStreamReader(stderr1, "GBK");
				BufferedReader br11 = new BufferedReader(isr1);
				String line1 = null;
				while ((line1 = br11.readLine()) != null)
					if (line1.contains("Transductive L2_SVM_MFN took")) {
						System.out.println(line1);
						String path = "/usr/local/svmlin-v1.0/out/time" + k;
						FileOutputStream fileout = new FileOutputStream(path, true);
						String qq = line1.substring(29, line1.length() - 9);
						// System.out.println(qq);
						try {
							byte[] br1 = qq.getBytes();
							fileout.write(br1);
							byte[] br2 = "\n".getBytes();
							fileout.write(br2);
							fileout.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				process1.waitFor();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String[] comman0 = { "/usr/local/svmlin-v1.0/svmlin", "-A", "3", "-W", "0.001", "-U", "1", "-R", "0.43", f1,
					f2 };

			Process process1;
			try {
				process1 = runt0.exec(comman0);
				process1.getInputStream();
				InputStream stderr1 = process1.getInputStream();
				InputStreamReader isr1 = new InputStreamReader(stderr1, "GBK");
				BufferedReader br11 = new BufferedReader(isr1);
				String line1 = null;
				while ((line1 = br11.readLine()) != null) {
					if (line1.contains("DA-SVM took")) {
						System.out.println(line1);
						String path = "/usr/local/svmlin-v1.0/out/timeDA" + k;
						FileOutputStream fileout = new FileOutputStream(path, true);
						String qq = line1.substring(12, line1.length() - 9);
						try {
							byte[] br1 = qq.getBytes();
							fileout.write(br1);
							byte[] br2 = "\n".getBytes();
							fileout.write(br2);
							fileout.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				// System.out.println(1);
				process1.waitFor();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Runtime runt = Runtime.getRuntime();
		String t1 = "/usr/workspace/LinSvm/aussvm_tr" + i + "_" + k + ".weights";
		String t2 = "/usr/local/svmlin-v1.0/example/aussvm_te" + i + "_" + k;
		String t3 = "/usr/local/svmlin-v1.0/example/aussvm_te_la" + i + "_" + k;
		String[] comman = { "/usr/local/svmlin-v1.0/svmlin", "-f", t1, t2, t3 };
		System.out.println(comman[0] + " " + comman[1] + " " + comman[2] + " " + comman[3] + " " + comman[4]);
		Process process;
		try {
			process = runt.exec(comman);
			InputStream stderr = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(stderr, "GBK");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String path = "/usr/local/svmlin-v1.0/out/log" + k;
				FileOutputStream fileout = new FileOutputStream(path, true);
				if (line.contains("Accuracy")) {
					String qq = line.substring(11, line.length() - 2);
					// System.out.println(qq);
					try {
						System.out.println(222);
						byte[] br1 = qq.getBytes();
						fileout.write(br1);
						byte[] br2 = "\n".getBytes();
						fileout.write(br2);
						fileout.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			process.waitFor();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void pretest(int i, int k) {
		String path = "/usr/local/svmlin-v1.0/example/data/aussvm_tr" + i;
		String path2 = "/usr/local/svmlin-v1.0/example/data/aussvm_tr_la" + i;
		File file = new File(path);
		File file2 = new File(path2);
		List<String> list = new ArrayList<String>();// 训练数据
		List<String> lable = new ArrayList<String>();// 训练数据标签
		List<String> list_test = new ArrayList<String>();// 测试数据
		List<String> lable_test = new ArrayList<String>();// 测试数据标签
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				list.add(line + "\n");
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				// System.out.println(line);
				lable.add(line + "\n");
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int m = 0; m < k; m++) {
			if (m != i) {
				String patht = "/usr/local/svmlin-v1.0/example/data/aussvm_tr" + m;
				String patht2 = "/usr/local/svmlin-v1.0/example/data/aussvm_tr_la" + m;
				File filet = new File(patht);
				File filet2 = new File(patht2);

				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filet)));
					for (String line = br.readLine(); line != null; line = br.readLine()) {
						// System.out.println(line);
						list.add(line + "\n");
						list_test.add(line + "\n");
						lable.add("0" + "\n");
					}
					br.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filet2)));
					for (String line = br.readLine(); line != null; line = br.readLine()) {
						// System.out.println(line);
						lable_test.add(line + "\n");
					}
					br.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		// 输出到文件
		try {
			FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/aussvm_tr" + i + "_" + k);
			FileOutputStream fileout2 = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/aussvm_tr_la" + i + "_" + k);
			for (int n = 0; n < list.size(); n++) {
				byte[] br1 = list.get(n).getBytes();
				fileout.write(br1);
			}
			for (int n = 0; n < lable.size(); n++) {
				byte[] br1 = lable.get(n).getBytes();
				fileout2.write(br1);
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
		try {
			FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/aussvm_te" + i + "_" + k);
			FileOutputStream fileout2 = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/aussvm_te_la" + i + "_" + k);
			for (int n = 0; n < list_test.size(); n++) {
				byte[] br1 = list_test.get(n).getBytes();
				fileout.write(br1);
			}
			for (int n = 0; n < lable_test.size(); n++) {
				byte[] br1 = lable_test.get(n).getBytes();
				fileout2.write(br1);
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

	}

}
