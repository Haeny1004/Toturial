package kr.sys4u.server;

import java.util.HashMap;
import java.util.Map;

import kr.sys4u.server.tasker.TalkTasker;
import kr.sys4u.server.tasker.Tasker;

public class ServerCommander {

	private final ChatRunner chatRunner;
	private final Map<String, Tasker> taskerMap = new HashMap<>();
	private boolean initialized = false;
	
	public ServerCommander(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		taskerMap.put("TALK", new TalkTasker(chatRunner));
		taskerMap.put("INFO", new InfoTasker(chatRunner));
		initialized = true;
	}
	
	public Tasker getTasker(String command) {
		if(!initialized) {
			init();
		}
		return taskerMap.get(command);
	}
}
