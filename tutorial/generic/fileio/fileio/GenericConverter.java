package com.tutorial.fileio;

public interface GenericConverter<S, R> {
	public R convert(S source);
}
