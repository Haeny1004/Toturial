package kr.sys4u.server;

import java.util.HashMap;
import java.util.Map;

import kr.sys4u.server.tasker.InfoTasker;
import kr.sys4u.server.tasker.RoomClientListTasker;
import kr.sys4u.server.tasker.RoomEnterTasker;
import kr.sys4u.server.tasker.RoomListTasker;
import kr.sys4u.server.tasker.RoomMakeTasker;
import kr.sys4u.server.tasker.RoomQuitTasker;
import kr.sys4u.server.tasker.TalkTasker;
import kr.sys4u.server.tasker.Tasker;

public class ServerCommander {

	private final Map<String, Tasker> taskerMap = new HashMap<>();
	private final ChatRunner chatRunner;
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
		taskerMap.put("MKROOM", new RoomMakeTasker(chatRunner));
		taskerMap.put("ENTERROOM", new RoomEnterTasker(chatRunner));
		taskerMap.put("CLIENTLIST", new RoomClientListTasker(chatRunner));
		taskerMap.put("QUITROOM", new RoomQuitTasker(chatRunner));
		taskerMap.put("ROOMLIST", new RoomListTasker(chatRunner));
		initialized = true;
	}
	
	public Tasker getTasker(String command) {
		if(!initialized) {
			init();
		}
		return taskerMap.get(command.toUpperCase());
	}
}
