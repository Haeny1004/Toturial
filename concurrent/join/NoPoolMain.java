package com.concurrent.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoPoolMain {
	
	private static List<Integer> randomNumbers = new ArrayList<>();
	
	public static void main(String[] args) {

		RandomNumberToken rrn = new RandomNumberToken();
		for (int i = 0; i < 100; i++) {
			Thread NumberGetterThread = new Thread(rrn);
			NumberGetterThread.start();
			try {
				NumberGetterThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			randomNumbers.add(rrn.getRandomNumber());
		}
		
		Collections.sort(randomNumbers);
		for(Integer number : randomNumbers) {
			System.out.println(number);
		}
		
	}
	
	

}
