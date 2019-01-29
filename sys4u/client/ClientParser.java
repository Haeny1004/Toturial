package kr.sys4u.client;

import java.util.StringTokenizer;

public class ClientParser {

	private static final String COMMAND_DELIM = "|";
	
	public String parseMessage(String nextLine) {
		StringTokenizer tokenizer = new StringTokenizer(nextLine, COMMAND_DELIM);
		if(tokenizer.nextToken().equals("TALK")) {
			return nextLine;
		}
		return tokenizer.nextToken();
	}
	
}
