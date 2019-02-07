package kr.sys4u.http.socket.server;

import java.util.StringTokenizer;

public class CommandLineParser {

	private final String readLine;
	
	public CommandLineParser(String readLine) {
		this.readLine = readLine;
	}
	
	public CommandLine parseLine() {
		StringTokenizer tokenizer = new StringTokenizer(readLine, " ");
		String method = tokenizer.nextToken();
		String url = tokenizer.nextToken();
		String version = tokenizer.nextToken();
		
		String targetFileName = url.substring(url.lastIndexOf("/"));
		String [] fileNameAndExtension = targetFileName.split("\\.");
		boolean hasExtension = fileNameAndExtension.length >= 2;
		
		String extension = null;
		if(hasExtension) {
			extension = fileNameAndExtension[fileNameAndExtension.length - 1];
		}
		
		return new CommandLine(method, url, extension, version);
	}
	
}
