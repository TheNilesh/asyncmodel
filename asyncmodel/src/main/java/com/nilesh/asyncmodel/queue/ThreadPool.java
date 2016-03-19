package com.nilesh.asyncmodel.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.nilesh.asyncmodel.User;

public class ThreadPool {

	private static final int WORKER = 10;
	private static ThreadPool threadPool;
	ExecutorService executor;

	public static ThreadPool getInstance() {
		if (null == threadPool) {
			threadPool = new ThreadPool();
		}
		return threadPool;
	}

	private ThreadPool() {
	}

	public void initialize() {
		if (executor == null) {
			executor = Executors.newFixedThreadPool(WORKER);
			for (int i = 0; i< WORKER; i++) {
				executor.submit(new Worker());
			}
		} else if (executor.isShutdown()) {
			throw new RuntimeException("threadpool cant re-start");
		}
	}

	void shutdownAndAwaitTermination() {
		//ask queue to accept no more users
		UserQueue.getInstance().put(new User(null, null));
		
		executor.shutdown();
		try {
			// Wait a while for existing tasks to terminate
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow(); // Cancel currently executing tasks
				if (!executor.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
	
	public Future<?> submit(Callable<?> task) {
		return executor.submit(task);
	}

	public Future<?> submit(Runnable task) {
		return executor.submit(task);
	}

}
