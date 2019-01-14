package com.tutorial.fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.tutorial.fileio.exception.ConvertTreeException;

public class DirTreeFileConverter implements GenericConverter<DirNodeTree, File> {
	
	private final String filePath;
	
	public DirTreeFileConverter(String filePath) {
		if(filePath == null) {
			throw new IllegalArgumentException();
		}
		this.filePath = filePath;
	}
	
	@Override
	public File convert(DirNodeTree source) {
		if(source == null) {
			throw new IllegalArgumentException();
		}
		try(FileWriter writer = new FileWriter(filePath)) {
			writer.write(source.toString());
		} catch (IOException e) {
			throw new ConvertTreeException(e);
		}
		return new File(filePath);
	}
}
