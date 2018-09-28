package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(10001);

			Socket s1 = serverSocket.accept();
			PrintWriter out1 = new PrintWriter(s1.getOutputStream(), true);
			BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			System.out.println("client connected from " + s1.getInetAddress());

			Socket s2 = serverSocket.accept();
			System.out.println("client connected from " + s2.getInetAddress());
			PrintWriter out2 = new PrintWriter(s2.getOutputStream(), true);
			BufferedReader in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));

			EchoThread r1 = new EchoThread(out1, in2);
			Thread t1 = new Thread(r1);
			t1.start();

			EchoThread r2 = new EchoThread(out2, in1);
			Thread t2 = new Thread(r2);
			t2.start();

		} catch (Throwable e) {

			System.out.println("Server couldnt connect!!!");
		}

	}
}
