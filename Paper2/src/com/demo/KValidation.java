package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KValidation {

	public void kValidation(int k, int t) {

		for (int i = 0; i < k; i++) {
			Date date = new Date();
			System.out.println(i + "ci" + date);
			System.out.println(i + "ci" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
			if (t == 0 | t == 1) {
				pretest1(i, k);
			} else {
				pretest(i, k);
			}
			test(i, k, t);
			String key1 = "japsvm_te" + i + "_" + k + ".outputs";
			String key2 = "japsvm_te_la" + i + "_" + k;
			// if (t == 0) {
			// key1 = "japsvm_te" + i + "_" + k + ".outputs";
			// //new GetPrecision().getPrecision1(key1, key2, k);
			// }
			new GetPrecision().getPrecision1(key1, key2, k,t);
			//new GetPrecision().getPrecision2(key1, key2, k);
			new GetRecall().getRecall(key1,key2,k,t);
		}
		new Out().jap(k, t, "/usr/local/svmlin-v1.0/out/five");

	}

	private void test(int i1, int k1, int t) {
		int i = i1;
		int k = k1;
		Runtime runt0 = Runtime.getRuntime();
		String f1 = "/usr/local/svmlin-v1.0/example/japsvm_tr" + i + "_" + k;
		String f2 = "/usr/local/svmlin-v1.0/example/japsvm_tr_la" + i + "_" + k;
		if (t == 2) {// TSVM
			String[] comman0 = { "/usr/local/svmlin-v1.0/svmlin", "-A", "2", "-W", "0.01", "-U", "1", "-R", "0.3976", f1,
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
						String path = "/usr/local/svmlin-v1.0/out/time2/time" + k;
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
		} else if (t == 0) {// RLS
			String[] comman0 = { "/usr/local/svmlin-v1.0/svmlin", "-A", "0", "-W", "0.01", "-U", "1", "-R", "0.3976", f1,
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
					System.out.println(line1);
					if (line1.contains("CGLS converged in")) {
						System.out.println(line1);
						Pattern p = Pattern.compile("and (.*) seconds.");
						Matcher m = p.matcher(line1);
						m.find();
						String qq=m.group(1);
						String path = "/usr/local/svmlin-v1.0/out/time/time" + k;
						FileOutputStream fileout = new FileOutputStream(path, true);
						//String qq = line1.substring(38, line1.length() - 9);
						System.out.println(qq);
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

				process1.waitFor();
			} catch (IOException | InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		} else if (t == 1) {
			// l2SVM
			String[] comman0 = { "/usr/local/svmlin-v1.0/svmlin", "-A", "1", "-W", "0.1", "-U", "1", "-R", "0.3976", f1,
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
					System.out.println(line1);
					if (line1.contains("L2_SVM_MFN converged (optimality) in")) {
						System.out.println(line1);
						Pattern p = Pattern.compile("and (.*) seconds.");
						Matcher m = p.matcher(line1);
						m.find();
						String qq=m.group(1);
						String path = "/usr/local/svmlin-v1.0/out/time1/time" + k;
						FileOutputStream fileout = new FileOutputStream(path, true);
						//String qq = line1.substring(56, line1.length() - 9);
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
				}

				process1.waitFor();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if(t==3){// DAS3VM
			String[] comman0 = { "/usr/local/svmlin-v1.1/svmlin", "-A", "3", "-W", "0.001", "-U", "1", "-R", "0.3976", f1,
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
					//System.out.println(line1);
					if (line1.contains("DA-SVM took")) {
						System.out.println(line1);
						String path = "/usr/local/svmlin-v1.0/out/time3/timeDA" + k;
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
		}else {// DAS3VMS
			String[] comman0 = { "/usr/local/svmlin-v1.1/svmlin", "-A", "4", "-W", "0.001", "-U", "1", "-R", "0.3976", f1,
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
					//System.out.println(line1);
					if (line1.contains("DA-SVM took")) {
						System.out.println(line1);
						String path = "/usr/local/svmlin-v1.0/out/time3/timeDA" + k;
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
		String t1 = "/usr/workspace/Paper2/japsvm_tr" + i + "_" + k + ".weights";
		String t2 = "/usr/local/svmlin-v1.0/example/japsvm_te" + i + "_" + k;
		String t3 = "/usr/local/svmlin-v1.0/example/japsvm_te_la" + i + "_" + k;
		String[] comman = { "/usr/local/svmlin-v1.1/svmlin", "-f", t1, t2, t3 };
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
				String path = "/usr/local/svmlin-v1.0/out/log"+t + k;
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

	public static void pretest(int i1, int k1) {
		int i = i1;
		int k = k1;
		String path = "/usr/local/svmlin-v1.0/example/data/test" + i;
		String path2 = "/usr/local/svmlin-v1.0/example/data/label" + i;
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
				String patht = "/usr/local/svmlin-v1.0/example/data/test" + m;
				String patht2 = "/usr/local/svmlin-v1.0/example/data/label" + m;
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
			FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/japsvm_tr" + i + "_" + k);
			FileOutputStream fileout2 = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/japsvm_tr_la" + i + "_" + k);
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
			FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/japsvm_te" + i + "_" + k);
			FileOutputStream fileout2 = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/japsvm_te_la" + i + "_" + k);
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

	public static void pretest1(int i1, int k1) {
		int i = i1;
		int k = k1;
		String path = "/usr/local/svmlin-v1.0/example/data/test" + i;
		String path2 = "/usr/local/svmlin-v1.0/example/data/label" + i;
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
				String patht = "/usr/local/svmlin-v1.0/example/data/test" + m;
				String patht2 = "/usr/local/svmlin-v1.0/example/data/label" + m;
				File filet = new File(patht);
				File filet2 = new File(patht2);

				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filet)));
					for (String line = br.readLine(); line != null; line = br.readLine()) {
						// System.out.println(line);
						// list.add(line + "\n");
						list_test.add(line + "\n");
						// lable.add("0" + "\n");
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
			FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/japsvm_tr" + i + "_" + k);
			FileOutputStream fileout2 = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/japsvm_tr_la" + i + "_" + k);
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
			FileOutputStream fileout = new FileOutputStream("/usr/local/svmlin-v1.0/example/japsvm_te" + i + "_" + k);
			FileOutputStream fileout2 = new FileOutputStream(
					"/usr/local/svmlin-v1.0/example/japsvm_te_la" + i + "_" + k);
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
