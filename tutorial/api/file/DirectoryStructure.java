package com.tutorial.api.file;

import java.io.File;

public class DirectoryStructure {
	
	private static final String DEFAULT_ROOT_DIRACTORY = "C:/";
	private static final String CRNL = "\r\n"; 
	
	private final File rootDirectory;
	private final StringBuilder resultStringBuilder = new StringBuilder();
	
	public DirectoryStructure(String strRootDirectory) {
		super();
		
		if(strRootDirectory == null) {
			strRootDirectory = DEFAULT_ROOT_DIRACTORY;
		}
		this.rootDirectory = new File(strRootDirectory);
		
		// java.io.File / java.nio.Path
		validate();
		
	}
	
	public DirectoryStructure(File rootDirectory) {
		if(rootDirectory == null) {
			this.rootDirectory = new File(DEFAULT_ROOT_DIRACTORY);
		}else {
			this.rootDirectory = rootDirectory;
		}
		
		validate();
		
	}
	
	public void validate() {
		if(!this.rootDirectory.exists()) {
			throw new IllegalArgumentException("rootDirectory[" + rootDirectory.getAbsolutePath() + "] does not exist.");
		}
	}

	public String getDirectoryStructureString() {
		System.out.println("Root Directory Length = [" + rootDirectory.length() + "]");
		
		traverseDirectory(rootDirectory);
		
		return resultStringBuilder.toString();
	}
	
	private void traverseDirectory(File parentDirectory) {
		File[] children = parentDirectory.listFiles();
		for (File child : children) {
			if(child.isFile()) {
				continue;
			}
			
			resultStringBuilder.append(child.getName());
			traverseDirectory(child);
		}
	}
	

	public static void main(String[] args) {
		System.out.println(new DirectoryStructure("C:/JEONG").getDirectoryStructureString());
	}

}
