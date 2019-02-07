package kr.sys4u.http.jacarta;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class Client implements Closeable{

	private URL url;
	private InputStream in;
	private boolean initialized = false;
	
	private void init() {
		if(initialized) {
			return;
		}
		try {
//			url = new URL("https://www.naver.com");
			url = new URL("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=da");
			in = url.openStream();
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
		try {
			System.out.println(IOUtils.toString(in, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
