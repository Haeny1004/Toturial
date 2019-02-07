package kr.sys4u.http.jacarta;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Client2 implements Closeable{

	private CloseableHttpClient httpClient;
	private HttpGet httpGet;
	private CloseableHttpResponse httpResponse;
	private BufferedReader reader;
	
	private boolean initialized = false;
	
	private void init() {
		if(initialized) {
			return;
		}
		httpClient = HttpClients.createDefault();
		httpGet = new HttpGet("https://www.naver.com");
		initialized = true;
	}
	
	private void execute() throws IOException {
		if(!initialized) {
			init();
		}
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		String readLine = null;
		while((readLine = reader.readLine()) != null) {
			System.out.println(readLine);
		}
	}
	
	@Override
	public void close() throws IOException {
		if(!initialized) {
			return;
		}
		reader.close();
		httpResponse.close();
		httpClient.close();
	}
	
	public static void main(String[] args) {
		try(Client2 client = new Client2()){
			client.init();
			client.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
