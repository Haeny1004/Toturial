package com.concurrent.test;

import java.util.concurrent.ThreadFactory;

public class NamedThreadsFactory implements ThreadFactory{

	private static int count = 0;
	private static String Name = "MyThread-";

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, Name + ++count);
	}
	
}
