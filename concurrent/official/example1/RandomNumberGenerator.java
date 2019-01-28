package com.concurrent.official.example1;

import java.util.Random;

public class RandomNumberGenerator implements Runnable{

	private int number;

	@Override
	public void run() {
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			//ignored
		}
		number = new Random().nextInt(100);
	}

	public int getNumber() {
		return this.number;
	}
}
