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

public class FileDirTreeConverter implements GenericConverter<File, DirNodeTree> {

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
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(source), "UTF-8"))) {
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
		if(lineNumber == readLineList.size()) {
			return;
		}
		
		String readLine = readLineList.get(lineNumber);
		int parentDepth = parentNode.getDepth();
		int childDepth = readLine.indexOf(CHILD_SYMBOL);
		
		if(parentDepth < childDepth) {
			setParentAndChildByLine(parentNode, readLine);
		}else if(parentDepth == childDepth) {
			setParentAndChildByLine(parentNode.getParentNode(), readLine);
		}else {
			parentNode = findParentNode(parentNode, parentDepth - childDepth + 1);
			setParentAndChildByLine(parentNode, readLine);
		}
	}
	
	private void setParentAndChildByLine(DirNode parentNode, String readLine) {
		File childFile = new File(parentNode.getCurrentDir().getAbsoluteFile() + "/" + readLine.substring(readLine.indexOf(CHILD_SYMBOL) + 1));
		DirNode childNode = new DirNode(readLine.indexOf(CHILD_SYMBOL), childFile);
		childNode.setParentNode(parentNode);
		parentNode.addChild(childNode);
		lineNumber++;
		addChildrenNodeRecursively(childNode);
	}
	
	private DirNode findParentNode(DirNode parentNode ,int loopLimit) {
		for (int i = 0; i < loopLimit; i++) {
			parentNode = parentNode.getParentNode();
		}
		return parentNode;
	}

} // End of class
