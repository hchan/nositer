package com.nositer.antisamy;

import com.nositer.util.HTMLPurifier;

public class TestHTMLPurifier {
	
	public static void main(String[] args) {
		try {
			System.out.println(HTMLPurifier.getCleanHTML("llllllllll<span style=\"font-weight: bold;\">llllll</span>"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
