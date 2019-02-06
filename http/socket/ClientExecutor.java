package kr.sys4u.http.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientExecutor {

	private final Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private boolean initialized = false;

	public ClientExecutor(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	private void init() {
		if (initialized) {
			return;
		}
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialized = true;
	}

	public void execute() {
		if (!initialized) {
			init();
		}

		sendRequest();
		try {
			readResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendRequest() {
		out.println(":authority : www.naver.com\\r\\n\n"
				+ ":method : GET\\r\\n\n"
				+ ":path : /\\r\\n\n"
				+ ":scheme : https\\r\\n\n"
				+ "accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\\r\\n\n"
				+ "accept-encoding: gzip, deflate, br\\r\\n\n"
				+ "accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\\r\\n\n"
				+ "referer: https://www.naver.com/\\r\\n\n"
				+ "cookie: NNB=OTXXIGDSNIJVY; npic=adUTgAjV+JEeXJi0OfvRUhGhZucoKctRQTjPPcTrrAy3CZTX4jH83XCWi/ASxCT9CA==; NID_AUT=TzuSU/vKb9RdpDnaEah600EzW7Vdklyji9IpIeJY7f96yymYjJnNyM2MyCbjb0Yh; NID_JKL=WH0Y2tA+r6GwBG/WVJgPLluxDxlDCnFqp/Z52doGY5A=; NaverSuggestUse=unuse%26unuse; nx_ssl=2; _ga=GA1.2.325322956.1549345535; _gid=GA1.2.1675765273.1549345535; PM_CK_loc=a9e96b6168e27a55d80d6ae05edaa0893a7b0cc087c9cf05a3f7f1cb5b9b7db0; NID_SES=AAABkxQ08Xo6loX8krQuqYJVi85YUyY6y39rcGK5w2+eMtlVPkZ4b1Z1a/0vBKQ8wA/PAk7lFcoWU9+HMOMm+4iR9Tq6m/Zpj2cpQAx5SqJGFfPinKFXCSn4kjHGm+KBVUVQUlcodMlfELLPRompNOb3TFN41trVhwd27J5ymtLYri59D+PRKEIsmgQU4fnfBT8AgiZh4l5Q/srMY4u1+V8++GG52SLrhRKiqUFPr3kwamW8WzyZcKRDypojvANTzQkQpVg2J8xan5yi6JUTbr7OfFkRdyIDED9ST572Tn5dCjlV+QJqlMZ2QNNAmwxZWbl8A6KABMZ55WtFLV/xe5GZ9fNfy08RJjdqX24GXOmEes7GZnIMz3g07q6VWNYbyoOxs0BHOc5m7FNIwaNOv3j158PA7oVLDHgTTsbwEZXWDOvijJCBSUVwbmYfJLW+5rxQmMLILOxm2RM11hoblJI6DEZZiFcFFJroxX6AHw7bFO3UbMRd6PIwiyqZBauHM8dCuQRno4MLwTWfXdq007wOyOnDRkwGa3Nl+eCrqRvc6t5q; BMR=s=1549359191482&r=https%3A%2F%2Fm.blog.naver.com%2Fluon%2F90142874603&r2=https%3A%2F%2Fwww.google.com%2F; page_uid=UaDi2spySElsstap1rwssssssK0-446369\\r\\n\n"
				+ "upgrade-insecure-requests: 1\\r\\n\n"
				+ "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
		
//		out.println("GET / HTTP/1.1\\r\\n\n"
//						+ "accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\\r\\n\n"
//						+ "accept-encoding: gzip, deflate, br\\r\\n\n"
//						+ "accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\\r\\n\n"
//						+ "referer: https://www.naver.com/\\r\\n\n"
//						+ "upgrade-insecure-requests: 1\\r\\n\n"
//						+ "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
		out.flush();
		System.out.println("Request Soccess");
	}

	private void readResponse() throws IOException {
		System.out.println("[ Response Message ]");
		String readLine = null;
		while (true) {
			if ((readLine = in.readLine()) != null) {
				System.out.println(readLine);
			}
		}
	}

}
