package com.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zzhe {

	public static void main(String[] args) {
		String line = "CGLS converged in 8 iteration(s) and 7e-06 seconds.";
		Pattern p = Pattern.compile("and (.*) seconds.");
		Matcher m = p.matcher(line);
		while (m.find()) {
			System.out.println(m.group(1));
		}
	}

}
