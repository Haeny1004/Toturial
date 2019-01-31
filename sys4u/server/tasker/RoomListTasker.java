package kr.sys4u.server.tasker;

import java.util.Map;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ServerSender;
import kr.sys4u.server.room.Room;

public class RoomListTasker implements Tasker {

	private final ChatRunner chatRunner;
	private Map<String, Room> chatRooms;
	private boolean initialized = false;

	public RoomListTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}

	private void init() {
		if (initialized) {
			return;
		}
		chatRooms = chatRunner.getChatRooms();
		initialized = true;
	}

	@Override
	public void task(String content) {
		if (!initialized) {
			init();
		}
		synchronized (chatRooms) {
			StringBuffer roomListBuffer = new StringBuffer();
			if (chatRooms.isEmpty()) {
				new ServerSender(chatRunner).sendMessageToMe("* 현재 생성되어 있는 방이 없습니다.");
				return;
			}
			roomListBuffer.append("[현재 생성되어 있는 방 목록]\n");
			for (String title : chatRooms.keySet()) {
				roomListBuffer.append(title + "\n");
			}
			new ServerSender(chatRunner).sendMessageToMe(roomListBuffer.toString());
		}
	}

}
