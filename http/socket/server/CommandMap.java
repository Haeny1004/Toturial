package kr.sys4u.http.socket.server;

import java.util.HashMap;
import java.util.Map;

import kr.sys4u.http.socket.server.responser.Responser;

public class CommandMap {

	private static volatile Map<String, Responser> instance = new HashMap<>();  
	private CommandMap() {
		
	}
	
	public Map<String, Responser> getInstance(){
		return instance;
	}
}
