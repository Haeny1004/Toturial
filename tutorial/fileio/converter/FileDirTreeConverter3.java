package com.tutorial.fileio.converter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.tutorial.fileio.DirNode;
import com.tutorial.fileio.DirNodeTree;
import com.tutorial.fileio.exception.ConversionException;

public class FileDirTreeConverter3 implements GenericConverter<File, DirNodeTree> {

	private static final String CHILD_SYMBOL = "┗";

	private DirNodeTree dirNodeTree;

	@Override
	public DirNodeTree convert(File source) {
		if(source == null) {
			throw new IllegalArgumentException();
		}
		createRootNode(initialize(source));
		return dirNodeTree;
	}

	private List<String> initialize(File source) {
		List<String> readLineList = new ArrayList<>();
		try {
			Files.lines(source.toPath()).sequential().forEach(readLineList::add);
		} catch (IOException e) {
			throw new ConversionException(e);
		}

		String readLine = readLineList.get(0);
		dirNodeTree = new DirNodeTree(readLine);
		return readLineList;
	}

	private void createRootNode(List<String> readLineList) {
		DirNode beforeNode = dirNodeTree.getRootNode();
		
		for (int i = 1; i < readLineList.size(); i++) {
			DirNode currentNode = addChildNode(beforeNode, readLineList.get(i));
			beforeNode = currentNode;
		}
	}
	
	private DirNode addChildNode(DirNode beforeNode, String readLine) {

		DirNode ancestorNode = beforeNode.getAncestor(beforeNode.getDepth() - readLine.indexOf(CHILD_SYMBOL) + 1);
		DirNode currentNode = new DirNode(readLine.indexOf(CHILD_SYMBOL), 
						new File(ancestorNode.getCurrentDir().getAbsolutePath() + "/" + readLine.substring(readLine.indexOf(CHILD_SYMBOL) + 1)));
		ancestorNode.addChild(currentNode);
		
		return currentNode;
	}


} // End of class
