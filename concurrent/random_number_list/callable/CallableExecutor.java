package com.concurrent.random_number_list.callable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CallableExecutor {
	private ExecutorService executor;
	private List<Integer> generated = new ArrayList<>();

	public void init() {
		executor = Executors.newFixedThreadPool(100);
	}
	
	public void shutdown() {
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void execute(List<CallableRandomNumberGenerator> generators) {
		long startTime = System.nanoTime();
		List<Future<Integer>> futureIntegers = null;
		try {
			futureIntegers = executor.invokeAll(generators, 5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		futureIntegers.stream().forEach(future -> {
			try {
				generated.add(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println("Elapsted Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
	}
	
	public static void main(String[] args) {
		final List<CallableRandomNumberGenerator> generators = new ArrayList<>();
		IntStream.range(0, 100).forEach(i -> generators.add(new CallableRandomNumberGenerator()));
		
		CallableExecutor callableExecutor = new CallableExecutor();
		callableExecutor.init();
		callableExecutor.execute(generators);
		callableExecutor.shutdown();

		// sorting
		Collections.sort(callableExecutor.generated);

		// printing
		callableExecutor.generated.stream().forEach(System.out::println);
	}
}
