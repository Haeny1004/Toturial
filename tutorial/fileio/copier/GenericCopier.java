package com.tutorial.fileio.copier;

public interface GenericCopier<S, R> {
	public void copy(S targetSource, R copiedSource);
	
	public void move(S targetSource, R copiedSource);
}
