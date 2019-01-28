package com.concurrent.official.example1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlainThreadExecutor {

	public static void main(String[] args) {
		final int count = 100;
		final List<Integer> generatedIntegers = new ArrayList<>();
		final List<RandomNumberGenerator> generators = new ArrayList<>();
		final List<Thread> threads = new ArrayList<>();
		
		long startTime = System.nanoTime();
		//execute threads
		for(int i = 0; i < count; i++) {
			RandomNumberGenerator generator = new RandomNumberGenerator();
			generators.add(generator);
			Thread thread = new Thread(generator);
			thread.start();
			threads.add(thread);
		}
		
		//waiting
		for(Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Elapsted Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
		
		//getting
		for (RandomNumberGenerator generator : generators) {
			generatedIntegers.add(generator.getNumber());
		}
		
		//sorting
		Collections.sort(generatedIntegers);
		
		//printing
		generatedIntegers.stream().forEach(System.out::println);
	}

}
