package com.concurrent.unique_number_list.synchronization;

import java.util.List;
import java.util.Random;

public class RandomNumberSetter implements Runnable {

	private final List<Integer> numbers;

	public RandomNumberSetter(List<Integer> numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (numbers) {
			int randomNumber = 0;

			if (numbers.isEmpty()) {
				randomNumber = new Random().nextInt(100);
				numbers.add(randomNumber);
				return;
			}

			do {
				randomNumber = new Random().nextInt(100);
			} while (numbers.contains(randomNumber));
			numbers.add(randomNumber);
		}
	}

}
