package com.tutorial.api.file2;

public class Main {

	public static void main(String[] args) {
		DirectoryNodeTree structor = new DirectoryNodeTree("C:/JEONG");
		
		System.out.println(new StructureConverter(structor.getRootNode()).convert());
		
		
	}

}
