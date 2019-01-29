package kr.sys4u.server;

public class Room {

	private String no;
	private String title;
	private int clientCount;
	private boolean attendable = true;
	
	public Room(String no, String title, int clientCount, boolean attendable) {
		this.no = no;
		this.title = title;
		this.clientCount = clientCount;
		this.attendable = attendable;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getClientCount() {
		return clientCount;
	}

	public void setClientCount(int clientCount) {
		this.clientCount = clientCount;
	}

	public boolean isAttendable() {
		return attendable;
	}

	public void setAttendable(boolean attendable) {
		this.attendable = attendable;
	}
	
}
