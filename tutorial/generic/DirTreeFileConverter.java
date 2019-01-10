package com.tutorial.generic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.tutorial.generic.exception.ConvertTreeException;
import com.tutorial.generic.exception.InitializeIOException;
import com.tutorial.generic.exception.StreamerCloseException;

public class DirTreeFileConverter implements GenericConverter<DirNode, File> {
	
	private static final String SPACE ="  ";
	private static final String CRNL = "\r\n";
	private static final String CHILD_SYMBOL = "┗";
	
	private boolean initialized = false;
	private final String filePath;
	private File convertedFile;
	private FileWriter streamer;
	private DirNode dirTreeNode;
	
	public DirTreeFileConverter(String filePath) {
		if(filePath == null) {
			throw new IllegalArgumentException();
		}
		this.filePath = filePath;
	}
	
	private void initialize(DirNode source) {
		if(initialized) {
			return;
		}
		convertedFile = new File(filePath);
		
		try {
			convertedFile.createNewFile();
			streamer = new FileWriter(convertedFile);
		} catch (IOException e) {
			throw new InitializeIOException(e);
		}
		this.dirTreeNode = source;
		initialized = true;
	}

	@Override
	public File convert(DirNode source) {
		if(source == null) {
			throw new IllegalArgumentException();
		}
		initialize(source);
		convertTreeRecursively(dirTreeNode);
		if(streamer != null) {
			try {
				streamer.close();
			} catch (IOException e) {
				throw new StreamerCloseException(e);
			}
		}
		return convertedFile;
	}
	
	private void convertTreeRecursively(DirNode parentNode) {
		try {
			streamer.write(new StringBuilder().append(printSpaceUpToDepth(parentNode.getDepth()))
											  .append(CHILD_SYMBOL)
											  .append(parentNode.getCurrentDir().getName())
											  .append(CRNL)
											  .toString());
		} catch (IOException e) {
			throw new ConvertTreeException(e);
		} 
		
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
}
