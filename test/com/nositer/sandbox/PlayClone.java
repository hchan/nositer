package com.nositer.sandbox;

import java.util.Date;

import com.nositer.client.dto.generated.User;

public class PlayClone {
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setNote("ABC");
		User myClone = (User) user.clone();
		System.out.println(myClone.getNote());
		
		
	}
}
