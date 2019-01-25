package com.concurrent.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LoopTask implements Runnable {
	
	private static int count = 0;
	private int id;
	private String taskId;
	private CountDownLatch doneCountLatch;
	
	public LoopTask(CountDownLatch doneCountLatch) {
		this.id = ++count;
		this.taskId = "LoopTask" + id;
		this.doneCountLatch = doneCountLatch;
	}

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("#### <" + currentThreadName + "," + taskId + "> starting...####");
		
		for (int i = 0; i < 5; i++) {
			System.out.println("<" + currentThreadName + "," + taskId + "> TICK TICK" + i);
			try {
				TimeUnit.MILLISECONDS.sleep((long) Math.random() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("#### <" + currentThreadName + "," + taskId + "> done...####");
		if (doneCountLatch != null) {
			doneCountLatch.countDown(); // count--
			System.out.println(
					"#### <" + currentThreadName + "," + taskId + "> LATCH COUNT =" + doneCountLatch.getCount());
		}
	}
}
