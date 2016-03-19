package com.nilesh.asyncmodel.executorservice;

import com.nilesh.asyncmodel.User;
import com.nilesh.asyncmodel.UserService;

public class UserServiceImpl implements UserService {

	public void insertUser(User user) {
		Worker worker = new Worker(user);
		ThreadPool.getInstance().submit(worker);
	}
	
	public void close() {
		ThreadPool.getInstance().shutdownAndAwaitTermination();
	}

	public void init() {
		ThreadPool.getInstance().initialize();
	}
}
