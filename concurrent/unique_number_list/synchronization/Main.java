package com.concurrent.unique_number_list.synchronization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	private static List<Integer> numbers = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 100; i++) {
			new Thread(new RandomNumberSetter(numbers)).start();
		}
		
		while(true) {
			if(Thread.activeCount() == 1) {
				Collections.sort(numbers);
				for(int i = 0; i < numbers.size(); i++) {
					System.out.println(i + "번째 Number : [ " + numbers.get(i) + " ]");
				}
				return;
			}
			System.out.println(Thread.activeCount());
		}
	}
}
