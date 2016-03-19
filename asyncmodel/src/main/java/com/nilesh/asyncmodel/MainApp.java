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
		System.out.println("Before starting " + System.currentTimeMillis());
		userService.init();
		System.out.println("Before insert " + System.currentTimeMillis());
		insertFakeUsers(userService);
		System.out.println("After insert " + System.currentTimeMillis());
		userService.close();
		System.out.println("After Close " + System.currentTimeMillis());
	
	}
	
	public static void insertFakeUsers(UserService userService) {
		for (int i=0; i<1000; i++) {
			User user = createFakeUser();
			userService.insertUser(user);
		}
	}
	
	private static User createFakeUser() {
		return new User("nilesh", "Pa$$W0rd");
	}
}
