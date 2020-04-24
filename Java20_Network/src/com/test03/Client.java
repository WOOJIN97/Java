package com.test03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Client());
		t1.start();
	}

	@Override
	public void run() {
		System.out.println("server에 접속합니다....");
		
		try {
			Socket clientSocket = null;
			PrintWriter out;
			BufferedReader in = null;
			BufferedReader stdIn = null;
			clientSocket = new Socket("localhost",666);
			
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			String inputLine;
			while( (inputLine=stdIn.readLine()) != null) {
				out.println(inputLine);
				System.out.println("server로부터"+"다시 받은 메시지: "+in.readLine());
			}
			
			stdIn.close();
			out.close();
			in.close();
			clientSocket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}