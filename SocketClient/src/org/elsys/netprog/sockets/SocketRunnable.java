package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SocketRunnable implements Runnable {

	PrintWriter out;
	BufferedReader in;
	BufferedReader stdIn;
	public SocketRunnable(PrintWriter out, BufferedReader in, BufferedReader stdIn) {
		super();
		this.out = out;
		this.in = in;
		this.stdIn = stdIn;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		String userInput = null;
		try {
					while ((userInput = stdIn.readLine()) != null){
				
				out.println(userInput);
				
				
			}
		} catch (Throwable e) {
			System.out.println("Error");
		}
	}
}
