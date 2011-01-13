package com.nositer.hibernate;

import com.nositer.util.Encrypt;

public class TestEncrypt {
	public static void main(String[] args) throws Exception {
		System.out.println(Encrypt.cryptPassword("password"));
	}
}
