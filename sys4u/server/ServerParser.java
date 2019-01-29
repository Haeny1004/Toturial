package kr.sys4u.server;

import java.util.StringTokenizer;

public class ServerParser {

	private static final String COMMAND_DELIM = "|";
	private String command;
	private String contend;
	
	public ServerParser() {

	}
	
	public void parseMsg(String msg) {
		StringTokenizer tokenizer = new StringTokenizer(msg, COMMAND_DELIM);
		command = tokenizer.nextToken();
		contend = tokenizer.nextToken();
	}

	public String getCommand() {
		return command;
	}

	public String getContend() {
		return contend;
	}
	
}
