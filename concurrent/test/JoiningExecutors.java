package com.concurrent.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JoiningExecutors {

	public static void main(String argc[]) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "]" + " thread starts here...");

		ExecutorService execService1 = Executors.newCachedThreadPool(new NamedThreadsFactory());
		CountDownLatch doneSignal = new CountDownLatch(4); // set the initial count

		execService1.execute(new LoopTask(doneSignal));
		execService1.execute(new LoopTask(doneSignal));
		execService1.execute(new LoopTask(doneSignal));
		execService1.execute(new LoopTask(doneSignal));

		execService1.shutdown();

		try {
			doneSignal.await(); // wait for the count = 0
			System.out.println("[" + currentThreadName + "]" + " got the signal to continue...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("[" + currentThreadName + "]" + " thread ends here...");
	}
}

