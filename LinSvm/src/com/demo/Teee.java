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

public class Teee {
	public static final int X =138;

	private static void read(InputStream inputStream, PrintStream out) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {

				if (line.contains("Accuracy")) {
					out.println(line);
					String qq = line.substring(11, line.length() - 2);
					// System.out.println(qq);
					try {
						String path = "/usr/local/svmlin-v1.0/log" + X + ".txt";

						FileOutputStream fileout = new FileOutputStream(path, true);

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

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void readD(InputStream inputStream, PrintStream out) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {

				if (line.contains("Accuracy")) {
					out.println(line);
					String qq = line.substring(11, line.length() - 2);
					try {
						String path = "/usr/local/svmlin-v1.0/log" + X + "_d.txt";

						FileOutputStream fileout = new FileOutputStream(path, true);

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

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void readProcessOutput(final Process process) {
		// 将进程的正常输出在 System.out 中打印，进程的错误输出在 System.err 中打印

		read(process.getInputStream(), System.out);
		read(process.getErrorStream(), System.err);
	}

	private static void readProcessOutputD(final Process process) {
		// 将进程的正常输出在 System.out 中打印，进程的错误输出在 System.err 中打印

		readD(process.getInputStream(), System.out);
		readD(process.getErrorStream(), System.err);
	}

	private static void jap_TSVM() throws IOException, InterruptedException {
		Jap_n xx=new Jap_n();
		xx.Randd(X);
		Runtime runt0 = Runtime.getRuntime();
		String f1 = "/usr/local/svmlin-v1.0/example/japsvm_train" + X + ".txt";
		String f2 = "/usr/local/svmlin-v1.0/example/japsvm_tr_lable" + X + ".txt";
		String[] comman0 = { "/usr/local/svmlin-v1.0/svmlin", "-A", "2", "-W", "0.001", "-U", "1", "-R", "0.43", f1,
				f2 };
		// String[] comman = {"ls", "-l","/usr" };
		runt0.exec(comman0);

		Runtime runt = Runtime.getRuntime();
		String t1 = "/usr/workspace/LinSvm/japsvm_train" + X + ".txt.weights";
		String t2 = "/usr/local/svmlin-v1.0/example/japsvm_test" + X + ".txt";
		String t3 = "/usr/local/svmlin-v1.0/example/japsvm_te_lable" + X + ".txt";
		// String[] comman = { "/usr/local/svmlin-v1.0/svmlin", "-f",
		// "/usr/local/svmlin-v1.0/japsvm_train138.txt.weights",
		// "/usr/local/svmlin-v1.0/example/japsvm_test138.txt",
		// "/usr/local/svmlin-v1.0/example/japsvm_te_lable138.txt" };
		String[] comman = { "/usr/local/svmlin-v1.0/svmlin", "-f", t1, t2, t3 };
		// String[] comman = {"ls", "-l","/usr" };
		Process process = runt.exec(comman);

		readProcessOutput(process);

	}

	public void ave() throws IOException {
		String path = "/usr/local/svmlin-v1.0/log" + X + ".txt";
		File file = new File(path);
		double average=0;
		int yq=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			//System.out.println(Double.parseDouble(line));
			average=average+Double.parseDouble(line);
			yq++;
		}
		System.out.println(average/yq);
	}

	public void aved() throws IOException {
		String path = "/usr/local/svmlin-v1.0/log" + X + "_d.txt";
		File file = new File(path);
		double average=0;
		int yq=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			//System.out.println(Double.parseDouble(line));
			average=average+Double.parseDouble(line);
			yq++;
		}
		System.out.println(average/yq);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int hehe = 1;
		switch (hehe) {
		case 1:
			for (int i = 0; i < 100; i++) {// 日本tsvm
				jap_TSVM();
			}
			new Teee().ave();
			break;
		case 2:
			for (int i = 0; i < 100; i++) {// 日本dasvm
				jap_DASVM();
			}
			new Teee().aved();
			break;
		case 3:
			for (int i = 0; i < 30; i++) {// 德国tsvm
				ger_TSVM();
			}
			break;
		case 4:
			for (int i = 0; i < 30; i++) {// 德国dasvm
				ger_DASVM();
			}
			break;

		case 5:
			for (int i = 0; i < 30; i++) {// 澳大利亚tsvm
				aus_TSVM();
			}
			break;
		case 6:
			for (int i = 0; i < 30; i++) {// 澳大利亚dasvm
				aus_DASVM();
			}
		default:
			System.out.println("请输入正确的值：1,2,3,4,5,6");
		}
	}

	private static void aus_DASVM() {
		System.out.println("aus_DASVM");

	}

	private static void aus_TSVM() {
		// TODO Auto-generated method stub
		System.out.println("aus_DASVM");
	}

	private static void ger_DASVM() {
		// TODO Auto-generated method stub
		System.out.println("aus_DASVM");
	}

	private static void ger_TSVM() {
		// TODO Auto-generated method stub
		System.out.println("aus_DASVM");
	}

	private static void jap_DASVM() throws IOException {
		
		Jap_n xx=new Jap_n();
		xx.Randd(X);

		Runtime runt0 = Runtime.getRuntime();
		String f1 = "/usr/local/svmlin-v1.0/example/japsvm_train" + X + ".txt";
		String f2 = "/usr/local/svmlin-v1.0/example/japsvm_tr_lable" + X + ".txt";
		String[] comman0 = { "/usr/local/svmlin-v1.0/svmlin", "-A", "3", "-W", "0.001", "-U", "1", "-R", "0.43", f1,
				f2 };
		// String[] comman = {"ls", "-l","/usr" };
		runt0.exec(comman0);

		Runtime runt = Runtime.getRuntime();
		String t1 = "/usr/workspace/LinSvm/japsvm_train" + X + ".txt.weights";
		String t2 = "/usr/local/svmlin-v1.0/example/japsvm_test" + X + ".txt";
		String t3 = "/usr/local/svmlin-v1.0/example/japsvm_te_lable" + X + ".txt";
		// String[] comman = { "/usr/local/svmlin-v1.0/svmlin", "-f",
		// "/usr/local/svmlin-v1.0/japsvm_train138.txt.weights",
		// "/usr/local/svmlin-v1.0/example/japsvm_test138.txt",
		// "/usr/local/svmlin-v1.0/example/japsvm_te_lable138.txt" };
		String[] comman = { "/usr/local/svmlin-v1.0/svmlin", "-f", t1, t2, t3 };
		// String[] comman = {"ls", "-l","/usr" };
		Process process = runt.exec(comman);

		readProcessOutputD(process);

	}

}
