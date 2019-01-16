package com.tutorial.fileio;

import java.io.IOException;

import com.tutorial.fileio.copier.DirByPathCopier;

public class Main {

	public static void main(String[] args) throws IOException {

//		OneFileByPathCopier fileCopier = new OneFileByPathCopier();
//		fileCopier.move("C:/JEONG/file-structure.txt", "C:/JEONG/file-structure_move.txt");
		
		CopierManager copyManager = new CopierManager();
		copyManager.addCopier(String.class, String.class, new DirByPathCopier());
//		copyManager.requestCopy("C:/JEONG/test2", "C:/JEONG/test");
		copyManager.requestMove("C:/JEONG/test", "C:/JEONG/test2");
		
		
//		DirNodeTree dirTree = new DirNodeTree("C:/dirTreeTest");
//		ConverterManager manager = new ConverterManager();
//		manager.addConverter(DirNodeTree.class, String.class, new DirTreeStringConverter());
//		manager.addConverter(DirNodeTree.class, File.class, new DirTreeFileConverter("C:/fileStructure.txt"));
//		manager.addConverter(DirNodeTree.class, File.class, new DirTreeFileGenerator());
//		manager.addConverter(File.class, DirNodeTree.class, new FileDirTreeConverter());
//		manager.addConverter(File.class, DirNodeTree.class, new FileDirTreeConverter2());
//		manager.addConverter(File.class, DirNodeTree.class, new FileDirTreeConverter3());
//		String result = manager.requestConvert(dirTree, String.class);
//		System.out.println(result);
//		File resultFile = manager.requestConvert(dirTree, File.class);
//		System.out.println("FileName : [ " + resultFile.getAbsolutePath() + "]");
//		manager.requestConvert(dirTree, File.class);
//		DirNodeTree dirTree = manager.requestConvert(new File("C:/JEONG/file-structure.txt"), DirNodeTree.class);
//		System.out.println(manager.requestConvert(dirTree, String.class));
		
	}
}
