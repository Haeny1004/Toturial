package com.tutorial.fileio.converter;

public interface GenericConverter<S, R> {
	public R convert(S source);
}
