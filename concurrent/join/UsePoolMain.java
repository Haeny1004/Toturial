package com.concurrent.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsePoolMain {

	private static final ExecutorService executor = Executors.newFixedThreadPool(10);
	private static List<Integer> randomNumbers = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		
		long startTime = System.nanoTime();

		for (int i = 0; i < 100; i++) {
			CountDownLatch latch = new CountDownLatch(1);
			LatchRandomNumberToken rrn = new LatchRandomNumberToken(latch);
			executor.execute(rrn);
			latch.await();
			randomNumbers.add(rrn.getRandomNumber());
		}

		Collections.sort(randomNumbers);
		for (Integer number : randomNumbers) {
			System.out.println(number);
		}
		
		executor.shutdown();
		System.out.println("Elapsed Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
	}

}
