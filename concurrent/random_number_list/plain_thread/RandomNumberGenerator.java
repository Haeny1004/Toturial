package com.concurrent.random_number_list.plain_thread;

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
