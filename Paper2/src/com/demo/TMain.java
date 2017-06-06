package com.demo;

import java.io.IOException;

public class TMain {
	public static void main(String[] args) throws IOException, InterruptedException {
		int k =50;// 1对折数.0
		int total =2221;
		
		
		while(k<=100){
			new KFlod().kFlod(k, total);// 分为k组
			RLS(k);
			l2SVM(k);
			TSVM(k);
			DASVM(k);
			DASVMS(k);
			k=k+10;
		}
		
	}

	private static void DASVMS(int k) {
		new KValidation().kValidation(k, 4);// 做反k折实验
		
	}

	public static void RLS(int k) {

//		 new JapNormalization().japNormalization();// 归一化

		new KValidation().kValidation(k, 0);// 做反k折实验

	}

	public static void l2SVM(int k) {

		// new JapNormalization().japNormalization();// 归一化

		new KValidation().kValidation(k, 1);// 做反k折实验

	}

	public static void TSVM(int k) {

		// new JapNormalization().japNormalization();// 归一化

		new KValidation().kValidation(k, 2);// 做反k折实验

	}

	public static void DASVM(int k) {

		// new JapNormalization().japNormalization();// 归一化

		new KValidation().kValidation(k, 3);// 做反k折实验

	}

}
