package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketsClient {

	public static void main(String[] args) throws IOException {
		Socket echoSocket = null;
		try {
			echoSocket = new Socket("localhost", 10001);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			SocketRunnable r = new SocketRunnable (out, in, stdIn);
			Thread socketThread = new Thread(r);
			
			socketThread.start();
				while (true) {
					System.out.println("response: "  + in.readLine());
				}
			
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} 
		
	}

}
