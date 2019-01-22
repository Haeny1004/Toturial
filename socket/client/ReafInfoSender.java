package com.socket.client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ReafInfoSender {
	
	public void sendReadList(ArrayList<File> reafList, OutputStream out) {
		try(ObjectOutputStream objOut = new ObjectOutputStream(out)){
			objOut.writeObject(reafList);
			objOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
