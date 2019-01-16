package com.tutorial.fileio;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DirNode {

	private final int depth;
	private final List<DirNode> childDir;
	private File currentDir;
	private DirNode parentNode;
	
	public DirNode(final int depth, final File currentDir) {
		this.depth = depth;
		this.currentDir = currentDir;
		this.childDir = new ArrayList<>();
		this.parentNode = null;
	}
	
	public DirNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(DirNode parentNode) {
		this.parentNode = parentNode;
	}

	public int getDepth() {
		return depth;
	}
	
	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir;
	}

	public File getCurrentDir() {
		return currentDir;
	}
	
	public boolean addChild(DirNode childDS) {
		if(childDS == null) {
			throw new IllegalArgumentException();
		}
		childDS.setParentNode(this);
		return this.childDir.add(childDS);
	}
	
	public boolean addChildren(Collection<DirNode> children) {
		if(children == null) {
			throw new IllegalArgumentException();
		}
		return this.childDir.addAll(children);
	}
	
	public DirNode getAncestor(int count) {
		DirNode ancestorNode = this;
		for (int i = 0; i < count; i++) {
			ancestorNode = ancestorNode.getParentNode();
		}
		return ancestorNode;
	}

	/* 
	 * 조회기능을 위한 방법
	 * 1. 복사
	 * 2. UnModifiable ??
	 * 
	 * */
	public List<DirNode> getChildDir(){
		List<DirNode> cloned = new ArrayList<>();
		cloned.addAll(this.childDir);
		return cloned;
	}
	
	public void removeChildren() {
		this.childDir.removeAll(this.childDir);
	}
}
