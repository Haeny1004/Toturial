package com.tutorial.generic;

public interface GenericConverter<S, R> {
	public R convert(S source);
}
