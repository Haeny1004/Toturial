package com.tutorial.api.file2;

import java.io.File;

public class DirectoryNodeTree {
	
	private static final String DEFAULT_ROOT_DIRECTORY = "C:/";
	
	private final DirNode rootNode;
	private boolean initialized = false;

	public DirectoryNodeTree(final File rootDirectory) {
		
		if(rootDirectory == null) {
			throw new IllegalArgumentException();
		}
		
		this.rootNode = new DirNode(0, rootDirectory);
		directoryValidate();
		
	}
	
	public DirectoryNodeTree(String rootDirectoryPath){
		
		if(rootDirectoryPath == null) {
			rootDirectoryPath = DEFAULT_ROOT_DIRECTORY;
		}
		
		this.rootNode = new DirNode(0,new File(rootDirectoryPath));
		directoryValidate();
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

}
