package com.concurrent.official.example4;

import java.util.List;
import java.util.Random;

public class RandomNumberAdder implements Runnable{
	private List<Integer> generated;
	
	public RandomNumberAdder(List<Integer> generated) {
		this.generated = generated;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			//ignored
		}
		
		while(true) {
			int randomNumber = new Random().nextInt(100);
			//check-and-insert
			synchronized(generated) {
				if(!generated.contains(randomNumber)) {
					generated.add(randomNumber);
					return;
				}
			}
		}
	}
}
