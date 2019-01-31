package kr.sys4u.server.tasker;

import java.util.List;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ClientInfo;
import kr.sys4u.server.ServerSender;

public class RoomClientListTasker implements Tasker{

	private final ChatRunner chatRunner;
	private List<ClientInfo> clients;
	private boolean initialized = false;
	
	public RoomClientListTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}
	
	private void init() {
		if(initialized) {
			return;
		}
		clients = chatRunner.getChatRooms().get(chatRunner.getClientInfo().getRoomTitle()).getClients();
		initialized = true;
	}
	
	@Override
	public void task(String content) {
		if(!initialized) {
			init();
		}
		StringBuffer clientListBuffer = new StringBuffer();
		clientListBuffer.append("[" + chatRunner.getClientInfo().getRoomTitle() + "방에 있는 유저리스트 ]\n");
		synchronized(clients) {
			for(ClientInfo clientInfo : clients) {
				clientListBuffer.append(clientInfo.getName() + "\n");
			}
			new ServerSender(chatRunner).sendMessageToMe(clientListBuffer.toString());
		}
	}

}
