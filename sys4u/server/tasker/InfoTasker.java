package kr.sys4u.server.tasker;

import java.util.StringTokenizer;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ClientInfo;
import kr.sys4u.server.ServerSender;

public class InfoTasker implements Tasker {

	private static final String INFO_DELIM = "@";
	private final ChatRunner chatRunner;
	private ClientInfo clientInfo;
	private boolean initialized = false;
	
	public InfoTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		clientInfo = chatRunner.getClientInfo();
		initialized = true;
	}
	
	@Override
	public void task(String content) {
		if(!initialized) {
			init();
		}
		StringTokenizer tokenizer = new StringTokenizer(content, INFO_DELIM);
		clientInfo.setRoomTitle(tokenizer.nextToken());
		clientInfo.setName(tokenizer.nextToken());
		new ServerSender(chatRunner).sendMessageToAll(clientInfo.getName() + "님이 접속하셨습니다.");
	}

}
