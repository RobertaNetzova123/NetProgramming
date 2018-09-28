package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class EchoThread implements Runnable {

	PrintWriter out;
	BufferedReader in;

	public EchoThread(PrintWriter out, BufferedReader in) {
		super();
		this.out = out;
		this.in = in;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		String inputLine;

		// new Thread(r).start();

		try {
			while ((inputLine = in.readLine()) != null) {
				out.println(inputLine);
				System.out.println(inputLine);
				if (inputLine.equals("exit"))
					return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
