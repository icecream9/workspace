package com.demo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomX {

	public static void main(String[] args) {
		int[] array = getRandomX(4800, 6000);
		System.out.println(array.length);

		// System.out.println(array[60]);
		// for (int i = 0; i < array.length; i++) {
		// System.out.println(array[i]);
		// }

	}

	public static int[] getRandomX(int X, int Y) {
		int x = X;// 个数
		int y = Y;// 随机范围
		Set<Integer> set = new HashSet<Integer>();
		Random random = new Random();
		// int qq=random.nextInt(1000);

		int[] array = new int[x];
		int num;
		for (; true;) {
			num = random.nextInt(y);
			// System.out.println(num);
			set.add(num);
			if (set.size() >= x) {
				break;
			}
		}
		int i = 0;
		for (int a : set) {
			array[i] = a;
			System.out.println(array[i]);
			i++;
		}
		return array;

	}
}
