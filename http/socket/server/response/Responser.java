package kr.sys4u.http.socket.server.response;

import java.io.File;

public interface Responser {
	
	public void response(File targetFile);
	
	public void cacheDataResponse(byte[] data);

}
