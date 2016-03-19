package com.nilesh.asyncmodel.queue;

import java.util.ArrayDeque;

import com.nilesh.asyncmodel.User;

public class UserQueue {

	private static UserQueue userQueue;
	private ArrayDeque<User> queue = new ArrayDeque<User>();
	
	public void put(User user) {
		queue.addLast(user);
	}
	
	public User get() {
		return queue.pollFirst();
	}

	public static UserQueue getInstance() {
		if (null == userQueue) {
			userQueue = new UserQueue();
		}
		
		return userQueue;
	}

}
