package com.nilesh.asyncmodel;

public interface UserService {
	/**
	 * Service will be ready to accept bulk operations, 
	 * after calling this. It may establish db connection, create threadpool etc.
	 */
	public void init();
	/**
	 * Inserts user in database
	 */
	public void insertUser(User user);
	/**
	 * Closes any database connection or thread pool
	 */
	public void close();

}
