package com.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.demo.RandomX;

public class GetRandom {

	public static void main(String[] args) {
		int x = 69;
		int y = 690;

		RandomX r = new RandomX();
		int[] array = r.getRandomX(x, y);
		Arrays.sort(array);
		// System.out.println(array.length);
		// for(int a : array){
		// System.out.print(a+" ");
		// }
		List<String> list = new ArrayList<String>();
		String path = "/media/root/EC6852336851FD30/Users/zj/Desktop/credit/japsvm_lab.txt";
		File file = new File(path);
		BufferedReader br;
		int q=0;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line = br.readLine(); line != null; line = br.readLine()) {

				if (Arrays.binarySearch(array, q) >= 0) {
					list.add(line + "\n");
				} else {
					list.add("0" + "\n");
				}
				q++;
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileOutputStream fileout = new FileOutputStream(
					"/media/root/EC6852336851FD30/Users/zj/Desktop/credit/japsvm_lab"+x+"_"+y+".txt");
			for (int k = 0; k < list.size(); k++) {

					byte[] br1 = list.get(k).getBytes();
					fileout.write(br1);

				
			
			}
			fileout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
