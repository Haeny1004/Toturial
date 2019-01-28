package com.concurrent.random_number_list.runnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.concurrent.random_number_list.plain_thread.RandomNumberGenerator;

public class ConcurrentExecutor {

	public static void main(String[] args) {
		int count = 100;
		ExecutorService executor = Executors.newFixedThreadPool(16);
		List<Integer> generated = new ArrayList<>();
		List<RandomNumberGenerator> generators = new ArrayList<>();

		long startTime = System.nanoTime();
		for (int i = 0; i < count; i++) {
			RandomNumberGenerator generator = new RandomNumberGenerator();
			generators.add(generator);

			executor.execute(generator);
		}

		executor.shutdown();
		try {// 내부적으로 join이 일어나고 있다는 얘기
			executor.awaitTermination(5 * 60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Elapsted Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");

		// getting
		generators.stream().forEach(generator -> generated.add(generator.getNumber()));

		// sorting
		Collections.sort(generated);

		// printing
		generated.stream().forEach(System.out::println);

	}

}
