package com.tutorial.generic;

public class DirTreeStringConverter implements GenericConverter<DirNode, String>{
	
	private static final String SPACE ="  ";
	private static final String CRNL = "\r\n";
	private static final String CHILD_SYMBOL = "┗";
	
	private final StringBuilder converted = new StringBuilder();
	private boolean initialized = false; 
	private DirNode dirNodeTree;
	
	@Override
	public String convert(DirNode rootNode) {
		if(rootNode == null) {
			throw new IllegalArgumentException();
		}
		initialize(rootNode);
		convertTreeRecursively(dirNodeTree);
		return converted.toString();
	}

	private synchronized void initialize(DirNode parentNode) {
		if(initialized) {
			return;
		}
		this.dirNodeTree = parentNode;
		initialized = true;
	}

	private void convertTreeRecursively(DirNode parentNode) {
		converted.append(printSpaceUpToDepth(parentNode.getDepth()))
				.append(CHILD_SYMBOL)
				.append(SPACE)
				.append(parentNode.getCurrentDir().getName())
				.append(CRNL);
		
		if(!parentNode.getChildDir().isEmpty()) {
			for(DirNode childNode : parentNode.getChildDir()) {
				convertTreeRecursively(childNode);
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
	
} // End of class
