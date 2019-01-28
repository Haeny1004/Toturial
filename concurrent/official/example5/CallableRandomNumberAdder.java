package com.concurrent.official.example5;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class CallableRandomNumberAdder implements Callable<List<Integer>>{

	private final List<Integer> generated;
	
	public CallableRandomNumberAdder(List<Integer> generated) {
		this.generated = generated;
	}
	
	@Override
	public List<Integer> call() throws Exception {
		Thread.sleep(10L);
		while(true) {
			int randomNumber = new Random().nextInt(100);
			if(!generated.contains(randomNumber)) {
				generated.add(randomNumber);
				return generated;
			}
		}
	}
}
