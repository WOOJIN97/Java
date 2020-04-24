package com.test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		//서버 생성
		ServerSocket serverSocket = null;
		//접속된 클라이언트의 소켓
		Socket serviceSocket;
		//출력 객체
		PrintWriter out = null;
		//클라이언트로부터 읽어드린 내용을
		//버퍼에 담을 객체
		BufferedReader in = null;
		
		try {
			serverSocket = new ServerSocket(666);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			System.out.println("client를 기다립니다.");
		
		try {
			serviceSocket = serverSocket.accept();
			System.out.println("client가 접속했습니다.");
			
			//client에게 받은 내용을 라인 단위로 읽어서
			in = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
			
			//보내주겠다.
			out = new PrintWriter(serviceSocket.getOutputStream(), true);
			
			String inputLine;
			while( (inputLine=in.readLine()) != null) {
				System.out.println("클라이언트가 보낸 메세지: " +inputLine);
				out.println(inputLine);
			}
		
			out.close();
			in.close();
			serviceSocket.close();
			serverSocket.close();
			}
		 	catch (IOException e) {
			e.printStackTrace();
			}
		}
	}
}