package com.tutorial.fileio;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
//		DirNodeTree dirTree = new DirNodeTree("C:/TEST");
		
		ConverterManager manager = new ConverterManager();
		manager.addConverter(DirNodeTree.class, String.class, new DirTreeStringConverter());
		manager.addConverter(DirNodeTree.class, File.class, new DirTreeFileConverter("c:/JEONG/file-structure.txt"));
		manager.addConverter(File.class, DirNodeTree.class, new FileDirTreeConverter());
		
//		String result = manager.requestConvert(dirTree, String.class);
//		System.out.println(result);
		
//		File resultFile = manager.requestConvert(dirTree, File.class);
//		System.out.println("FileName : [ " + resultFile.getAbsolutePath() + "]");
		manager.requestConvert(new File("C:/JEONG/file-structure.txt"), DirNodeTree.class);
	}
}
