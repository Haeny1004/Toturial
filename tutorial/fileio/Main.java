package com.tutorial.fileio;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
//		DirNodeTree dirTree = new DirNodeTree("C:/dirTreeTest");
		
		ConverterManager manager = new ConverterManager();
		manager.addConverter(DirNodeTree.class, String.class, new DirTreeStringConverter());
//		manager.addConverter(DirNodeTree.class, File.class, new DirTreeFileConverter("C:/fileStructure.txt"));
		manager.addConverter(DirNodeTree.class, File.class, new DirTreeFileGenerator());
//		manager.addConverter(File.class, DirNodeTree.class, new FileDirTreeConverter());
		manager.addConverter(File.class, DirNodeTree.class, new FileDirTreeConverter2());
		
//		String result = manager.requestConvert(dirTree, String.class);
//		System.out.println(result);
		
//		File resultFile = manager.requestConvert(dirTree, File.class);
//		System.out.println("FileName : [ " + resultFile.getAbsolutePath() + "]");
		
//		manager.requestConvert(dirTree, File.class);
		
		DirNodeTree dirTree = manager.requestConvert(new File("C:/filestructure.txt"), DirNodeTree.class);
		System.out.println(manager.requestConvert(dirTree, String.class));
		
		
	}
}
