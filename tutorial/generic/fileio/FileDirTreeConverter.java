package com.tutorial.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileDirTreeConverter implements GenericConverter<File, DirNodeTree> {

	private static final Character SPACE = '\t';
	private static final String CHILD_SYMBOL = "┗";

	private final List<String> readLineList = new ArrayList<>();
	private int lineNumber = 0;
	private final StringBuffer dirPath = new StringBuffer();

	@Override
	public DirNodeTree convert(File source) {
		initialize(source);
		dirPath.append(readLineList.get(lineNumber));
		lineNumber++;
		convertFileRecursively(0, dirPath);
		return null;
	}

	private void initialize(File source) {
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(source), "UTF-8"))) {
			String temp = "";
			while ((temp = bf.readLine()) != null) {
				readLineList.add(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void convertFileRecursively(int parentDepth, StringBuffer dirPath) {
		int childDepth = readLineList.get(lineNumber).indexOf(CHILD_SYMBOL);
		System.out.println(childDepth);
		
		if(parentDepth < childDepth) {
			dirPath.append("/").append(readLineList.get(lineNumber).substring(childDepth + 1));
			convertFileRecursively(childDepth, dirPath);
		}
		System.out.println(dirPath.toString());
//		new File(dirPath.toString()).mkdirs();
	}
}
