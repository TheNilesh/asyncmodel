package com.nilesh.asyncmodel.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

import com.nilesh.asyncmodel.User;

public class UserQueue {

	private static UserQueue userQueue;
	private LinkedBlockingQueue<User> queue = new LinkedBlockingQueue<User>();
	
	public void put(User user) {
		try {
			queue.put(user);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public User get() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static UserQueue getInstance() {
		if (null == userQueue) {
			userQueue = new UserQueue();
		}
		
		return userQueue;
	}
	
	public static void main(String[] args) {
		UserQueue.getInstance().put(null);
	}
}
