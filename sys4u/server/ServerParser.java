package kr.sys4u.server;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class ServerParser {

	private static final String COMMAND_DELIM = "|";
	private String command;
	private String content;

	public ServerParser() {
		this.command = null;
		this.content = null;
	}

	public void parseMsg(String msg) throws IllegalArgumentException{
		if(msg == null) {
			throw new IllegalArgumentException();
		}
		try {
			StringTokenizer tokenizer = new StringTokenizer(msg, COMMAND_DELIM);
			command = tokenizer.nextToken();
			content = tokenizer.nextToken();
		}catch(NoSuchElementException e) {
			return;
		}
	}

	public String getCommand() {
		return command;
	}

	public String getContent() {
		return content;
	}

}
