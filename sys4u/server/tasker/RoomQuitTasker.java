package kr.sys4u.server.tasker;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ClientInfo;
import kr.sys4u.server.ServerSender;
import kr.sys4u.server.room.Room;

public class RoomQuitTasker implements Tasker {
	
	private final ChatRunner chatRunner;
	
	public RoomQuitTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}
	
	@Override
	public void task(String content) {
		ClientInfo clientInfo = chatRunner.getClientInfo();
		
		Room room = chatRunner.getChatRooms().get(clientInfo.getRoomTitle());
		
		room.removeClient(clientInfo);
		synchronized(room) {
			if(!room.isAttendable()) {
				room.setAttendable(true);
			}
		}
		new ServerSender(chatRunner).sendMessageToAll(clientInfo.getName() + "님이 퇴장하셨습니다.");
		chatRunner.getClientInfo().setRoomTitle("DEFAULT");
	}

}
