package com.tutorial.fileio.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.tutorial.fileio.DirNode;
import com.tutorial.fileio.DirNodeTree;
import com.tutorial.fileio.exception.readAllLineException;

public class FileDirTreeConverter2 implements GenericConverter<File, DirNodeTree> {

	private static final String CHILD_SYMBOL = "┗";

	private final List<String> readLineList = new ArrayList<>();
	private int lineNumber = 0;
	private DirNodeTree dirNodeTree;

	@Override
	public DirNodeTree convert(File source) {
		if(source == null) {
			throw new IllegalArgumentException();
		}
		initialize(source);
		addChildrenNodeRecursively(dirNodeTree.getRootNode());
		return dirNodeTree;
	}

	private void initialize(File source) {
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(source), "MS949"))) {
			String temp = "";
			while ((temp = bf.readLine()) != null) {
				readLineList.add(temp);
			}
		} catch (IOException e) {
			throw new readAllLineException();
		}
		String readLine = readLineList.get(lineNumber); 
		dirNodeTree = new DirNodeTree(readLine);
		lineNumber++;
	}

	private void addChildrenNodeRecursively(DirNode parentNode) {
		String readLine = null;
		DirNode beforeNode = parentNode;
		DirNode currentNode = null;
		
		while(lineNumber < readLineList.size()) {
			
			readLine = readLineList.get(lineNumber);
			beforeNode = findParentNode(beforeNode, beforeNode.getDepth() - readLine.indexOf(CHILD_SYMBOL) + 1);
			currentNode = new DirNode(readLine.indexOf(CHILD_SYMBOL), new File(beforeNode.getCurrentDir().getAbsolutePath() + "/" + readLine.substring(readLine.indexOf(CHILD_SYMBOL) + 1)));
			beforeNode.addChild(currentNode);
			beforeNode = currentNode;
			
			lineNumber++;
		}
	}
	
	private DirNode findParentNode(DirNode parentNode ,int loopLimit) {
		for (int i = 0; i < loopLimit; i++) {
			parentNode = parentNode.getParentNode();
		}
		return parentNode;
	}

} // End of class
