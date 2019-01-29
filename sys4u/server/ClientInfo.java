package kr.sys4u.server;

public class ClientInfo {

	private String roomNumber;
	private String name;
	
	public ClientInfo(String roomNumber, String name) {
		this.roomNumber = roomNumber;
		this.name = name;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
