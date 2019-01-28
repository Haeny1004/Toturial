package com.concurrent.official.example5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableUniqueNumberExecutor {

	public static void main(String[] args) {
		final ExecutorService executor = Executors.newFixedThreadPool(100);
//		final ExecutorService executor = Executors.newSingleThreadExecutor();
		List<Integer> generated = new ArrayList<>();
		final List<CallableRandomNumberAdder> adders = new ArrayList<>();

//		IntStream.range(0, 100).forEach(i -> adders.add(new CallableRandomNumberAdder(generated)));

		
		long startTime = System.nanoTime();
//		for (CallableRandomNumberAdder adder : adders) {
//			executor.submit(adder);
//		}
		
		for (int i = 0; i < 100; i++) {
			Future<List<Integer>> future = executor.submit(new CallableRandomNumberAdder(generated));
			try {
				generated = future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Elapsted Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");

		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// sorting
		Collections.sort(generated);

		// printing
		generated.stream().forEach(System.out::println);

	}
}
