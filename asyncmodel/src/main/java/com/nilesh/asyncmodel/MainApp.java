package com.nilesh.asyncmodel;

public class MainApp {
	public static void main(String[] args) {
		System.out.println("Perfomance test of async service models");
		System.out.println("===================================");
		
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("Executors as queue");
		System.out.println("----------------------------------");
		UserService userService = ServiceFactory.getExcutorUserService();
		testUserService(userService);
		
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("Linked Blocking Queue");
		System.out.println("----------------------------------");
		userService = ServiceFactory.getBlockingQueueUserService();
		testUserService(userService);
		
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("Array Deque");
		System.out.println("----------------------------------");
		userService = ServiceFactory.getQueueUserService();
		testUserService(userService);
		
	}

	public static void testUserService(UserService userService) {
		
		long timeBefore = System.currentTimeMillis();
		userService.init();
		long timeAfter = System.currentTimeMillis();
		System.out.println("Time to init " + (timeAfter - timeBefore));
		
		timeBefore = System.currentTimeMillis();
		insertFakeUsers(userService);
		timeAfter = System.currentTimeMillis();
		System.out.println("Time to insert " + (timeAfter - timeBefore));
		
		timeBefore = System.currentTimeMillis();
		userService.close();
		timeAfter = System.currentTimeMillis();
		System.out.println("Time to close " + (timeAfter - timeBefore));
	}
	
	public static void insertFakeUsers(UserService userService) {
		for (int i=0; i<100; i++) {
			User user = createFakeUser();
			userService.insertUser(user);
		}
	}
	
	private static User createFakeUser() {
		return new User("nilesh", "Pa$$W0rd");
	}
}
