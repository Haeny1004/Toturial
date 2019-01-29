package kr.sys4u.server.tasker;

import java.util.List;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ServerSender;

public class TalkTasker implements Tasker{

	private final ChatRunner chatRunner;
	private List<ChatRunner> chatRunners;
	private boolean initialized = false;
	
	public TalkTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		chatRunners = chatRunner.getChatRunners();
		initialized = true;
	}
	
	@Override
	public void task(String content) {
		if(!initialized) {
			init();
		}
		new ServerSender(chatRunners).sendMessageToAll(content);
	}

}
