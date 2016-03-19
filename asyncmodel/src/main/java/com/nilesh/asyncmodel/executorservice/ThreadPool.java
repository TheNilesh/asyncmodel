package com.nilesh.asyncmodel.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

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
			executor = Executors.newFixedThreadPool(10);
		} else if (executor.isShutdown()) {
			throw new RuntimeException("threadpool cant re-start");
		}
	}

	void shutdownAndAwaitTermination() {
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
