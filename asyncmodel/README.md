# Producer Consumer Implementations

During developing of REST API, I was supposed to insert new records into database in background and return **202 Accepted** to client. I found 3 ways by which I can solve this asynchronously. I want to find the best one...?

1. Using executors as queue

```
    executor.submit(new Runnable () { //threadpool of size 10
			public void run() {
				new UserDao().insert(user);
			}
		 });
	return "202 Accepted";
```
		 
This way I can just create a new Thread per request and submit it to executor.

2. Using BlockingQueue
```
	queue.put(user);
	return "202 Accepted";
	
	public class Worker implements Runnable { //10 such threads of workers
		public void run() {
			while(true) {
				User user = queue.take(); // Call blocks if queue is empty
				new UserDao().insert(user);
			}
		}
	}
```	
 - Producer threads will be inserting new items into queue.
 - Worker(Consumer) threads will be removing from queue.

	
3. Using normal queue (ArrayDeque)
```
	queue.put(user);
	return "202 Accepted";
	
	public class Worker implements Runnable { //10 such threads of workers
		public void run() {
			while(true) {
				User user = queue.poll(); // Call blocks if queue is empty
				if (null != user) {
					new UserDao().insert(user);
				}
			}
		}
	}
```	
 - Producer threads will be inserting new items into queue.
 - Worker(Consumer) threads will be removing from this queue.
