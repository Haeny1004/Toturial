package com.tutorial.fileio.converter;

import com.tutorial.fileio.DirNodeTree;

public class DirTreeStringConverter implements GenericConverter<DirNodeTree, String>{
	
	@Override
	public String convert(DirNodeTree source) {
		if(source == null) {
			throw new IllegalArgumentException();
		}
		return source.toString();
	}
	
} // End of class
