package com.concurrent.unique_number_list.synchronization;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class LatchRandomNumSetter implements Runnable {

	private final List<Integer> numbers;
	private final CountDownLatch latch;
	
	public LatchRandomNumSetter(List<Integer> numbers, CountDownLatch latch) {
		this.numbers = numbers;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (numbers) {
			int randomNumber = 0;

			if (numbers.isEmpty()) {
				randomNumber = new Random().nextInt(100);
				numbers.add(randomNumber);
				latch.countDown();
				return;
			}

			do {
				randomNumber = new Random().nextInt(100);
			} while (numbers.contains(randomNumber));
			numbers.add(randomNumber);
			latch.countDown();
		}
	}

}
