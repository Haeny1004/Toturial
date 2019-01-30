package kr.sys4u.server.tasker;

import java.util.List;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ClientInfo;

public class RoomClientListTasker implements Tasker{

	private final ChatRunner chatRunner;
	private final List<ClientInfo> clients;
	
	public RoomClientListTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
		this.clients = chatRunner.getChatRooms().get(chatRunner.getClientInfo().getRoomTitle()).getClients();
	}
	
	@Override
	public void task(String content) {
		
		
		
	}

}
