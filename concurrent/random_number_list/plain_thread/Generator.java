package com.concurrent.random_number_list.plain_thread;

import java.util.Random;

public class Generator implements Runnable {

	private int number;
	
	@Override
	public void run() {
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		number = new Random().nextInt(100);
	}
	
	public int getNumber() {
		return this.number;
	}
}
