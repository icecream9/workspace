package com.demo;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		int k =5;// 反对折数
		int iter = 1;// 迭代次数
		int qtt =3;// 选择地方法
		int total = 2589;
		new KFlod().kFlod(k, total);// 分为k组
		switch (qtt) {
		case 0:
			System.out.println("This is case 0：Regularized Least Squares (RLS) Classification");

			for (int i = 0; i < iter; i++) {
				RLS(k);
			}

			break;
		case 1:
			System.out.println("This is case 1：- SVM (L2-SVM-MFN) (default choice)");

			for (int i = 0; i < iter; i++) {
				l2SVM(k);
			}

			break;
		case 2:
			System.out.println("This is case 2：Multi-switch Transductive SVM (using L2-SVM-MFN)");

			for (int i = 0; i < iter; i++) {
				TSVM(k);
			}

			break;
		case 3:
			System.out.println("This is case 3：Deterministic Annealing Semi-supervised SVM (using L2-SVM-MFN)");

			for (int i = 0; i < iter; i++) {
				DASVM(k);
			}

			break;

		default:
			System.out.println("请输入正确的值：0,1,2,3");
		}
	}

	public static void RLS(int k) {

		// new JapNormalization().japNormalization();// 归一化

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
