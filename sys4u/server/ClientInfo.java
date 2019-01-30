package kr.sys4u.server;

public class ClientInfo {

	private String roomTitle;
	private String name;
	
	public ClientInfo(String roomTitle, String name) {
		this.roomTitle = roomTitle;
		this.name = name;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomNumber) {
		this.roomTitle = roomNumber;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
