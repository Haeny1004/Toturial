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
	private static final String SUB_DIR_SYMBOL = "/";

	private final List<String> readLineList = new ArrayList<>();
	private int lineNumber = 0;
	private DirNodeTree dirNodeTree;

	@Override
	public DirNodeTree convert(File source) {
		initialize(source);
		convertFileRecursively();
		return null;
	}

	private void initialize(File source) {
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(source), "MS949"))) {
			String temp = "";
			while ((temp = bf.readLine()) != null) {
				readLineList.add(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File convertFileRecursively() {
		
		return null;
	}
}
