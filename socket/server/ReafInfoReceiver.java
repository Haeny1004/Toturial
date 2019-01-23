package com.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.socket.ReafFile;
import com.socket.exception.ReafFileListReadException;

public class ReafInfoReceiver {

	@SuppressWarnings("unchecked")
	public ArrayList<ReafFile> receiveReafList(InputStream in){
		ArrayList<ReafFile> reafList = null;
		try {
			ObjectInputStream objIn = new ObjectInputStream(in);
			reafList = (ArrayList<ReafFile>) objIn.readObject();
		} catch (ClassNotFoundException e) {
			throw new ReafFileListReadException(e);
		} catch (IOException e) {
			// 어떻게 처리할지 고민..
		}
		return reafList;
	}

}
