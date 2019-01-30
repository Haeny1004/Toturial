package kr.sys4u.server.tasker;

import java.util.StringTokenizer;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ServerSender;
import kr.sys4u.server.room.Room;

public class RoomEnterTasker implements Tasker {
	
	private static final String INFO_DELIM = "@";
	private final ChatRunner chatRunner;
	
	public RoomEnterTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}
	
	@Override
	public void task(String content) {
		if(content == null) {
			return;
		}
		//방 접속하기
		StringTokenizer tokenizer = new StringTokenizer(content, INFO_DELIM);
		if(tokenizer.countTokens() < 1) {
			return;
		}
		String title = tokenizer.nextToken();
		Room room = chatRunner.getChatRooms().get(title);
		
		synchronized(room) {
			if(!room.isAttendable()) {
				new ServerSender(chatRunner).sendMessageToMe(title + "방에 참가할 수 없습니다.");
				return;
			}
			
			chatRunner.getClientInfo().setRoomTitle(title);
			room.addClient(chatRunner.getClientInfo());
			if(Integer.valueOf(room.getLimit()) == room.getClients().size()) {
				room.setAttendable(false);
			}
		}
		new ServerSender(chatRunner).sendMessageToAll(title + "방에" + chatRunner.getClientInfo().getName() +"님이 접속하였습니다.");
		
	}

}
