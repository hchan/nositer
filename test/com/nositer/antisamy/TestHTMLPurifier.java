package com.nositer.antisamy;

import com.nositer.util.HTMLPurifier;

public class TestHTMLPurifier {
	
	public static void main(String[] args) {
		try {
			System.out.println(HTMLPurifier.getCleanHTML("<SCRIPT>alert();</SCRIPT>a<div class=\"ccc\">ddd</div>"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
