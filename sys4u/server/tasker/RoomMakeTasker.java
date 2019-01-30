package kr.sys4u.server.tasker;

import java.util.StringTokenizer;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ServerSender;
import kr.sys4u.server.room.Room;

public class RoomMakeTasker implements Tasker {

	private static final String INFO_DELIM = "@";
	private final ChatRunner chatRunner;
	private final Room room;
	
	public RoomMakeTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
		this.room = new Room(null, null);
	}
	
	@Override
	public void task(String content) {
		if(content == null) {
			return;
		}
		// 방 만들기
		StringTokenizer tokenizer = new StringTokenizer(content, INFO_DELIM);
		if(tokenizer.countTokens() < 2) {
			return;
		}
		String title = tokenizer.nextToken();
		String limit = tokenizer.nextToken();
		room.setTitle(title);
		room.setLimit(limit);
		
		if(chatRunner.getChatRooms().containsKey(title)) {
			new ServerSender(chatRunner).sendMessageToMe(title + "방은 이미 존재합니다.");
			return;
		}
		chatRunner.getChatRooms().put(title, room);
		
		// 만든 방 자동접속
		new ServerSender(chatRunner).sendMessageToAll(title + "방이 생성되었습니다.");
		new RoomEnterTasker(chatRunner).task("@"+title);
	}
	

}
