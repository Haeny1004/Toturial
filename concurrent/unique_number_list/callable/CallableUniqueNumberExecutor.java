package com.concurrent.unique_number_list.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableUniqueNumberExecutor {

	public static void main(String[] args) {
		Set<Integer> generated = new TreeSet<>();

		long startTime = System.nanoTime();
		while (generated.size() < 100) {
			List<Future<Integer>> futures = new ArrayList<>();
			ExecutorService executor = Executors.newFixedThreadPool(100);

			for (int i = 0; i < 100; i++) {
				futures.add(executor.submit(new CallableRandomNumberAdder()));
			}
			executor.shutdown();
			try {
				executor.awaitTermination(5, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (Future<Integer> future : futures) {
				if (generated.size() >= 100) {
					break;
				}
				try {
					generated.add(future.get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Elapsted Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");

		// printing
		generated.stream().forEach(System.out::println);

	}
}
