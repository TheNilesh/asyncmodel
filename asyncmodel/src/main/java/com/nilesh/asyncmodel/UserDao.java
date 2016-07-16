package com.nilesh.asyncmodel;

public class UserDao {

	public void insert(User user) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
