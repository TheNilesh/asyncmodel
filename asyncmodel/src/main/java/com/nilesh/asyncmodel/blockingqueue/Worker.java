package com.nilesh.asyncmodel.blockingqueue;

import com.nilesh.asyncmodel.User;
import com.nilesh.asyncmodel.UserDao;

public class Worker implements Runnable {

	public void run() {
		
		while(true) {
			User user = UserQueue.getInstance().get();
			if (null != user.getUserId()) {
				new UserDao().insert(user);
			} else {
				//We got poison pill, lets die
				break;
			}
		}
	}
}
