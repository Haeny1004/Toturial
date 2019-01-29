package kr.sys4u.server;

import java.util.StringTokenizer;

public class ServerMessageParser {

	private String task;
	private String content;

	public void parseMsg(String msg){
		StringTokenizer tokenizer = new StringTokenizer(msg, "|");
		task = tokenizer.nextToken();
		content = tokenizer.nextToken();
	}

	public String getTask() {
		return task;
	}

	public String getContent() {
		return content;
	}
	
}
