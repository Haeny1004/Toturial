package com.tutorial.fileio.converter;

import java.io.File;

import com.tutorial.fileio.DirNode;
import com.tutorial.fileio.DirNodeTree;

public class DirTreeFileGenerator implements GenericConverter<DirNodeTree, File> {

	private static final String CHILD_SYMBOL = "/";

	@Override
	public File convert(DirNodeTree source) {
		source.getRootNode().setCurrentDir(new File("C:/Test"));
		StringBuilder generateDirPath = new StringBuilder();
		generateDirPath.append(source.getRootNode().getCurrentDir().getAbsolutePath());
		mkdirsToChildRecursively(source.getRootNode(), generateDirPath);
		return new File("C:/Test");
	}

	private void mkdirsToChildRecursively(DirNode parentNode, StringBuilder parentDirPath) {
		if (!parentNode.getChildDir().isEmpty()) {
			for (DirNode childNode : parentNode.getChildDir()) {
				StringBuilder childDirPath = new StringBuilder(parentDirPath);
				mkdirsToChildRecursively(childNode, new StringBuilder(childDirPath.append(CHILD_SYMBOL).append(childNode.getCurrentDir().getName())));
			}
		}else {
			new File(parentDirPath.toString()).mkdirs();
		}
	}

} // End of class
