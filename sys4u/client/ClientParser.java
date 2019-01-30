package kr.sys4u.client;

public class ClientParser {

	private static final String COMMAND_DELIM = "|";
	
	public String parseMessage(String nextLine) {
		if(nextLine.contains(COMMAND_DELIM) || nextLine.isEmpty()) {
			return nextLine;
		}
		return "TALK|" + nextLine;
	}
	
}
