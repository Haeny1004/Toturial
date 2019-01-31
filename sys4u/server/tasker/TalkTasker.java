package kr.sys4u.server.tasker;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ServerSender;

public class TalkTasker implements Tasker{

	private final ChatRunner chatRunner;
	
	public TalkTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}
	
	@Override
	public void task(String content) {
		new ServerSender(chatRunner).sendMessageToAll(chatRunner.getClientInfo().getName() + "> " + content);
	}

}
