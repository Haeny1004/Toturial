package com.concurrent.random_number_list.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoPoolMain {
	
	private static List<Integer> randomNumbers = new ArrayList<>();
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		
		RandomNumberToken rnt = new RandomNumberToken();
		for (int i = 0; i < 100; i++) {
			Thread numberGetterThread = new Thread(rnt);
			numberGetterThread.start();
			try {
				numberGetterThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			randomNumbers.add(rnt.getRandomNumber());
		}
		
		Collections.sort(randomNumbers);
		for(Integer number : randomNumbers) {
			System.out.println(number);
		}
		
		System.out.println("Elapsed Time = " + (System.nanoTime() - startTime) / 1000_000 + "ms");
		
	}
	
	

}
