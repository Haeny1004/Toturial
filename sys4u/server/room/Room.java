package kr.sys4u.server.room;

import java.util.ArrayList;
import java.util.List;

import kr.sys4u.server.ClientInfo;

public class Room {

	private String title;
	private String limit;	// 타입...
	private List<ClientInfo> clients;
	private boolean attendable = true;
	
	public Room(String title, String limit) {
		this.title = title;
		this.limit = limit;
		this.clients = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public boolean isAttendable() {
		return attendable;
	}

	public void setAttendable(boolean attendable) {
		this.attendable = attendable;
	}

	public List<ClientInfo> getClients() {
		return clients;
	}

	public void addClient(ClientInfo clientInfo) {
		clients.add(clientInfo);
	}
	
	public void removeClient(ClientInfo clientInfo) {
		clients.remove(clientInfo);
	}
	
}
