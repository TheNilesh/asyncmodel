package com.nilesh.asyncmodel.blockingqueue;
import com.nilesh.asyncmodel.User;
import com.nilesh.asyncmodel.UserService;

public class UserServiceImpl implements UserService {
	
	public void init() {
		ThreadPool threadPool = ThreadPool.getInstance();
		//make workers ready
		threadPool.initialize();
	}
	
	public void insertUser(User user) {
		UserQueue.getInstance().put(user);
	}
	
	public void close() {
		ThreadPool.getInstance().shutdownAndAwaitTermination();
	}

}
