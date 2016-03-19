package com.nilesh.asyncmodel.executorservice;

import com.nilesh.asyncmodel.User;
import com.nilesh.asyncmodel.UserDao;

public class Worker implements Runnable {

	private User user;

	public Worker(User user) {
		this.user = user;
	}

	public void run() {
		new UserDao().insert(user);
	}
}
