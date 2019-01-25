package com.concurrent.synchronization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsePoolMain {

	private static final ExecutorService execService = Executors.newFixedThreadPool(10);
	private static final CountDownLatch latch = new CountDownLatch(100);
	
	private static List<Integer> numbers = new ArrayList<>();
	
	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 100; i++) {
			execService.execute(new LatchRandomNumSetter(numbers, latch));
		}
		
		latch.await();
		Collections.sort(numbers);
		for(int i = 0; i < numbers.size(); i++) {
			System.out.println(i + "번째 Number : [ " + numbers.get(i) + " ]");
		}
	}

}
