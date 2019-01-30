package kr.sys4u.server.tasker;

import kr.sys4u.server.ChatRunner;

public class QuitTasker implements Tasker{

	private final ChatRunner chatRunner;
	
	public QuitTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}
	
	@Override
	public void task(String content) {
		chatRunner.disConnect();
	}

}
