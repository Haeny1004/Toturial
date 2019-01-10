package com.tutorial.api.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTutorial {

	public static void main(String[] args) {
		System.out.println(printDirectoryConstruction("C:/JEONG"));
		
	} // End of main
	
	private static String printDirectoryConstruction(String path) {
		
		List<String> list = new ArrayList<>();
		StringBuilder result = new StringBuilder();
		int tab = 1;
		
		File[] files = new File(path).listFiles();
		
		for (int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()){
				list.add(files[i].getName() + "\n");
				findSubDir(files[i].getPath(), tab, list);
			}
		}
		
		for (String directory : list) {
			result.append(directory);
		}
		
		return result.toString();
	}
	
	private static void findSubDir(String path, int tab, List<String> list){
		
		String subTab = "";
		for (int i = 0; i < tab; i++) {
			subTab += "\t";
		}
		
		File[] files = new File(path).listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()){
				list.add(subTab + "┗" + files[i].getName() + "\n");
				findSubDir(files[i].getPath(), tab + 1, list);
			}
		}
		
	}

} // End of class