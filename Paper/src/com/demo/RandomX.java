package com.demo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomX {
	public static int[] getRandomX(int X, int Y) {
		int x = X;// 个数
		int y = Y;// 随机范围
		Set<Integer> set = new HashSet<Integer>();
		Random random = new Random();

		int[] array = new int[x];
		int num;
		for (; true;) {
			num = random.nextInt(y);
			set.add(num);
			if (set.size() >= x) {
				break;
			}
		}
		int i = 0;
		for (int a : set) {
			array[i] = a;
			//System.out.println(array[i]);
			i++;
		}
		return array;

	}
}
