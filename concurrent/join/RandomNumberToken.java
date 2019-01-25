package com.concurrent.join;

import java.util.Random;

public class RandomNumberToken implements Runnable {

	private int randomNumber;

	public int getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		randomNumber = new Random().nextInt(100);
	}

} // End of class
