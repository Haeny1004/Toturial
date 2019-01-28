package com.concurrent.official.example4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class UniqueNumberExecutor {
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
	
	public void execute(List<RandomNumberAdder> generators) {
		generators.stream().forEach(generator -> executor.execute(generator));
	}
	
	public static void main(String[] args) {
		UniqueNumberExecutor uniqueNumberExecutor = new UniqueNumberExecutor();
		final List<RandomNumberAdder> generators = new ArrayList<>();
		IntStream.range(0, 100).forEach(i -> generators.add(new RandomNumberAdder(uniqueNumberExecutor.generated)));
		
		UniqueNumberExecutor callableExecutor = new UniqueNumberExecutor();
		callableExecutor.init();
		long startTime = System.nanoTime();
		callableExecutor.execute(generators);
		System.out.println("Elapsted Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
		callableExecutor.shutdown();
		
		// sorting
		Collections.sort(uniqueNumberExecutor.generated);

		// printing
		uniqueNumberExecutor.generated.stream().forEach(System.out::println);
	}
}
