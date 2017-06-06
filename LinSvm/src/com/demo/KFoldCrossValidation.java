package com.demo;

public class KFoldCrossValidation {

	public static void main(String[] args) {
		int k = 5;// KFoldCrossValidation
		int hehe = 5;
		int C = 5;
		switch (hehe) {
		case 1:
			// 日本tsvm
			for (int i = 0; i < C; i++) {
				new KFoldCrossValidation().jap_TSVM(k);
			}

			break;
		case 2:
			// 日本dasvm
			for (int i = 0; i < C; i++) {
				new KFoldCrossValidation().jap_DASVM(k);
			}

			break;
		case 3:
			// 德国tsvm
			for (int i = 0; i < C; i++) {
				new KFoldCrossValidation().ger_TSVM(k);
			}
			break;
		case 4:
			// 德国dasvm
			for (int i = 0; i < C; i++) {
				new KFoldCrossValidation().ger_DASVM(k);
			}
			break;
		case 5:
			// 澳大利亚tsvm
			for (int i = 0; i < C; i++) {
				new KFoldCrossValidation().aus_TSVM(k);
			}
			break;
		case 6:
			// 澳大利亚dasvm
			for (int i = 0; i < C; i++) {
				new KFoldCrossValidation().aus_DASVM(k);
			}
			break;
		default:
			System.out.println("请输入正确的值：1,2,3,4,5,6");
		}
		new DealAll().dealAll("/usr/local/svmlin-v1.0/example");
		// new DealAll().dealAll("/usr/local/svmlin-v1.0/example");
	}

	private void jap_DASVM(int k1) {
		int k = k1;
		int total = 690;
		new JapNormalization().japNormalization();// 归一化
		new KFlod().kFlod(k, total);// 分为k组

		new KValidation().kValidation(k, 2);// 做反k折实验

	}

	private void jap_TSVM(int k1) {
		int k = k1;
		int total = 690;
		new JapNormalization().japNormalization();// 归一化
		new KFlod().kFlod(k, total);// 分为k组
		// System.out.println(339);
		new KValidation().kValidation(k, 1);// 做反k折实验

	}

	private void aus_DASVM(int k) {
		int total = 690;
		new AusNormalization().ausNormalization();// 归一化
		new KFlod().kFlodaus(k, total);// 分为k组
		new KValidationAus().kValidation(k, 2);// 做反k折实验

	}

	private void aus_TSVM(int k) {
		int total = 690;
		new AusNormalization().ausNormalization();// 归一化
		new KFlod().kFlodaus(k, total);// 分为k组
		new KValidationAus().kValidation(k, 1);// 做反k折实验

	}

	private void ger_DASVM(int k) {
		int total = 1000;
		new GerNormalization().gerNormalization();// 归一化
		new KFlod().kFlodger(k, total);// 分为k组
		new KValidationGer().kValidation(k, 2);// 做反k折实验
	}

	private void ger_TSVM(int k) {
		int total = 1000;
		new GerNormalization().gerNormalization();// 归一化
		new KFlod().kFlodger(k, total);// 分为k组
		new KValidationGer().kValidation(k, 1);// 做反k折实验

	}

}
