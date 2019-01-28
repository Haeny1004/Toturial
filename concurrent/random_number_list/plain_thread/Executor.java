package com.concurrent.random_number_list.plain_thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Executor {

	public static void main(String[] args) {
		final List<Integer> numbers = new ArrayList<>();
		final List<Generator> generators = new ArrayList<>();
		final List<Thread> threads = new ArrayList<>();
		
		long startTime = System.nanoTime();
		
		for (int i = 0; i < 100; i++) {
			Generator generator = new Generator();
			generators.add(generator);
			Thread thread = new Thread(generator);
			thread.start();
			threads.add(thread);
		}
		
		for(Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Elapsted Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
		
		for(Generator generator : generators) {
			numbers.add(generator.getNumber());
		}
		
		Collections.sort(numbers);
		
		numbers.stream().forEach(System.out::println);
	}

}
