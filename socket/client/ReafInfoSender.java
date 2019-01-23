package com.socket.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.socket.ReafFile;
import com.socket.exception.FileDataReadAndSendException;
import com.socket.exception.ReafListSendException;
import com.socket.exception.TargetFileNotFoundException;

public class ReafInfoSender {
	
	public void sendReafInfo(List<ReafFile> reafFileList, OutputStream out, InputStream in) throws IOException {
		DataInputStream intIn = new DataInputStream(in);
		sendReafList(reafFileList, out);
		int readInt = 0;
		while(true) {
			readInt = intIn.readInt();
			if(readInt == -1) {
				System.out.println("Client> Read List Number : " + readInt);
				return;
			}
			System.out.println("Client> Read List Number : " + readInt);
			System.out.println("Client> ReafFile By Number : " + reafFileList.get(readInt).getAbsolutePath() + 
					" / SiZE : [" + reafFileList.get(readInt).getLength() + "]");
			new ReafInfoSender().sendFileData(new File(reafFileList.get(readInt).getAbsolutePath()), out);
		}
	}
	
	private void sendReafList(List<ReafFile> reafList, OutputStream out) {
		try{
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(reafList);
			objOut.flush();
		} catch (IOException e) {
			throw new ReafListSendException(e);
		}
	}
	
	private void sendFileData(File targetFile, OutputStream out) {
		try (BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(targetFile));) {
			BufferedOutputStream fileOut = new BufferedOutputStream(out);
			
			byte[] buf = new byte[8192];
			int cnt = 0;
			for(int i = 0; i < targetFile.length(); i = i + cnt) {
				cnt = fileIn.read(buf);
				fileOut.write(buf, 0, cnt);
				fileOut.flush();
				System.out.println("현재 읽은 DataSize : " + (i + cnt));
			}
			System.out.println(targetFile.getName() + "전송이 끝났습니다");
		} catch (FileNotFoundException e) {
			throw new TargetFileNotFoundException(e);
		} catch (IOException e) {
			throw new FileDataReadAndSendException(e);
		}
	}
	
}
