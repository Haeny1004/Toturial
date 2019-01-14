package com.tutorial.fileio;

import java.io.File;
import java.util.List;

public class DirNodeTree {
	
	private static final String SPACE ="\t";
	private static final String CRNL = "\r\n";
	private static final String CHILD_SYMBOL = "┗";
	private static final String DEFAULT_ROOT_DIRECTORY = "C:/";
	
	private final DirNode rootNode;
	private boolean initialized = false;

	public DirNodeTree(final File rootDirectory) {
		
		if(rootDirectory == null) {
			throw new IllegalArgumentException();
		}
		
		this.rootNode = new DirNode(0, rootDirectory);
		directoryValidate();
		
	}
	
	public DirNodeTree(String rootDirectoryPath){
		
		if(rootDirectoryPath == null) {
			rootDirectoryPath = DEFAULT_ROOT_DIRECTORY;
		}
		
		this.rootNode = new DirNode(0,new File(rootDirectoryPath));
//		directoryValidate();
	}
	
	private void directoryValidate() {
		if(!rootNode.getCurrentDir().exists()) {
			throw new IllegalArgumentException("rootDirectory : [" + rootNode.getCurrentDir().getAbsolutePath() + " ] is null");
		}
	}
	
	public synchronized void initialize() {
		if(initialized) {
			return;
		}
		rootNode.removeChildren();
		addChildrenRecursivly(rootNode);
		initialized = true;
	}
	
	public DirNode getRootNode() {
		if(!initialized) {
			initialize();
		}
		return rootNode;
	}
	
	private void addChildrenRecursivly(DirNode parentNode) {
		if(parentNode.getChildDir().isEmpty()) {
			return;
		}
		File[] files = parentNode.getCurrentDir().listFiles();
		for (File childFile : files) {
			if(childFile.isFile()) {
				continue; 
			}
			DirNode childNode = new DirNode(parentNode.getDepth() + 1, childFile);
			addChildrenRecursivly(childNode);
			parentNode.addChild(childNode);
		}
		
	}
	
	@Override
	public String toString() {
		initialize();
		StringBuffer converted = new StringBuffer();
		converted.append(rootNode.getCurrentDir().getAbsolutePath() + CRNL);
		convertTreeRecursively(rootNode.getChildDir(), converted);
		return converted.toString();
	}
	
	private void convertTreeRecursively(List<DirNode> childDir, StringBuffer converted) {
		
		if(!childDir.isEmpty()) {
			for(DirNode childNode : childDir) {
				converted.append(printSpaceUpToDepth(childNode.getDepth()))
				.append(CHILD_SYMBOL)
				.append(childNode.getCurrentDir().getName())
				.append(CRNL);
				convertTreeRecursively(childNode.getChildDir(), converted);
			}
		}
	}

	private String printSpaceUpToDepth(int depth) {
		StringBuilder spaceStringBuilder = new StringBuilder();
		for( int i = 0; i < depth; i++ ) {
			spaceStringBuilder.append(SPACE);
		}
		return spaceStringBuilder.toString();
	}
}
