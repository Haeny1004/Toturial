package com.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.socket.ReafFile;

public class ReafInfoReceiver {
	
	@SuppressWarnings("unchecked")
	public ArrayList<ReafFile> receiveReafList(InputStream in) {
		ArrayList<ReafFile> reafList = null;
		try{
			ObjectInputStream objIn = new ObjectInputStream(in);
			reafList = (ArrayList<ReafFile>)objIn.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return reafList;
	}
	
}
