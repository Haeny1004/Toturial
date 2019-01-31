package kr.sys4u.server.tasker;

import java.util.StringTokenizer;

import kr.sys4u.server.ChatRunner;
import kr.sys4u.server.ServerSender;
import kr.sys4u.server.room.Room;

public class RoomEnterTasker implements Tasker {

	private static final String INFO_DELIM = "@";
	private final ChatRunner chatRunner;
	private Room targetRoom;

	public RoomEnterTasker(ChatRunner chatRunner) {
		this.chatRunner = chatRunner;
	}

	@Override
	public void task(String content) {
		if (content == null) {
			return;
		}

		StringTokenizer tokenizer = new StringTokenizer(content, INFO_DELIM);
		if (tokenizer.countTokens() != 2) {
			return;
		}

		String title = tokenizer.nextToken();
		try {
			targetRoom = getRoomByTitle(title);
		} catch (NullPointerException e) {
			new ServerSender(chatRunner).sendMessageToMe(title + "방은 존재하지 않습니다.");
			return;
		}

		synchronized (targetRoom) {
			if (targetRoom.getClients().contains(chatRunner.getClientInfo())) {
				new ServerSender(chatRunner).sendMessageToMe("이미 참가하고 있는 방 입니다.");
				return;
			}

			if (!targetRoom.isAttendable()) {
				new ServerSender(chatRunner).sendMessageToMe(title + "방에 참가할 수 없습니다.");
				return;
			}

			chatRunner.getClientInfo().setRoomTitle(title);
			targetRoom.addClient(chatRunner.getClientInfo());
			if (Integer.valueOf(targetRoom.getLimit()) == targetRoom.getClients().size()) {
				targetRoom.setAttendable(false);
			}
		}
		new ServerSender(chatRunner)
				.sendMessageToAll(title + "방에" + chatRunner.getClientInfo().getName() + "님이 접속하였습니다.");

	}

	private Room getRoomByTitle(String title) {
		if (chatRunner.getChatRooms().get(title) == null) {
			throw new NullPointerException();
		}
		return chatRunner.getChatRooms().get(title);
	}

}
