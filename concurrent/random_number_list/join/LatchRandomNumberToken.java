package com.concurrent.random_number_list.join;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class LatchRandomNumberToken implements Runnable {

	private int randomNumber;
	private final CountDownLatch latch;
	
	public LatchRandomNumberToken(CountDownLatch latch) {
		this.latch = latch;
	}

	public int getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		randomNumber = new Random().nextInt(100);
		latch.countDown();
	}

} // End of class
