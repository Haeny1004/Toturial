package kr.sys4u.http.httpurlconnection;

import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Client implements Closeable{
	
	private boolean initialized = false;
	private HttpURLConnection httpUrlConn;
	private URL url;
	
	private void init() {
		if(initialized) {
			return;
		}
		try {
//			url = new URL("https://www.naver.com");
			url = new URL("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=da");
			httpUrlConn = (HttpURLConnection)url.openConnection();
			httpUrlConn.setRequestMethod("GET");
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialized = true;
	}
	
	private void execute() {
		if(!initialized) {
			init();
		}
		new ClientExecutor(httpUrlConn).execute();
	}
	
	@Override
	public void close() throws IOException {
		if(!initialized) {
			return;
		}
	}
	
	public static void main(String[] args) {
		try(Client client = new Client()){
			client.init();
			client.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
