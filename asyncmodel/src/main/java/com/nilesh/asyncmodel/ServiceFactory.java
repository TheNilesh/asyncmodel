package com.nilesh.asyncmodel;

public class ServiceFactory {

	public static UserService getBlockingQueueUserService() {
		return new com.nilesh.asyncmodel.blockingqueue.UserServiceImpl();
	}
	
	public static UserService getExcutorUserService() {
		return new com.nilesh.asyncmodel.executorservice.UserServiceImpl();
	}
	
	public static UserService getQueueUserService() {
		return new com.nilesh.asyncmodel.queue.UserServiceImpl();
	}
}
