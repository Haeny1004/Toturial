package com.tutorial.generic;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		DirNode dirNode = new DirNodeTree("C:/JEONG").getRootNode();
		
		ConversionManager manager = new ConversionManager();
		manager.addConverter(DirNode.class ,String.class, new DirTreeStringConverter());
		manager.addConverter(DirNode.class, File.class, new DirTreeFileConverter("c:/JEONG/file-structure.txt"));
		
//		String result = manager.requestConvert(dirNode, String.class);
//		System.out.println(result);
		
		File resultFile = manager.requestConvert(dirNode, File.class);
		System.out.println("FileName : [ " + resultFile.getAbsolutePath() + "]");
	}
}
