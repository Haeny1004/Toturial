package com.tutorial.api.file2;

public class StructureConverter {
	
	private static final String SPACE = "\t";
	private static final String CRNL = "\r\n";
	private static final String CHILD_SYMBOL = "┗";
	
	private final StringBuilder converted = new StringBuilder();
	private final DirNode parentDS;
	
	public StructureConverter(DirNode parentDS) {
		if(parentDS == null) {
			throw new IllegalArgumentException();
		}
		this.parentDS = parentDS;
	}

	public String convert() {
		convertStructureRecursively(parentDS);
		return converted.toString();
	}
	
	public void convertStructureRecursively(DirNode parentDS) {
		
		converted.append(printSpaceUpToDepth(parentDS.getDepth()))
				 .append(CHILD_SYMBOL)
				 .append("["+parentDS.getCurrentDir().getName()+"]")
				 .append(CRNL);
		
		if(!parentDS.getChildDir().isEmpty()) {
			for (DirNode childDS : parentDS.getChildDir()) {
				convertStructureRecursively(childDS);
			}
		}
	}
	
	private String printSpaceUpToDepth(int depth) {
		StringBuilder spaceSB = new StringBuilder();
		
		for( int i = 0; i < depth; i++ ) {
			spaceSB.append(SPACE);
		}
		return spaceSB.toString();
	}
}
