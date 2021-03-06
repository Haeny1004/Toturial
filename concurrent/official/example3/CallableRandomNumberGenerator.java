package com.concurrent.official.example3;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableRandomNumberGenerator implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Thread.sleep(10L);
		return new Random().nextInt(100);
	}

}
