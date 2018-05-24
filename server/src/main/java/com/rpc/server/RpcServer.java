package com.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcServer {
	public static final int SERVER_PORT = 10000;

	public static void main(String[] args) throws IOException {
		new RpcServer().run();
	}

	private void run() throws IOException {
		ServerSocket listener = new ServerSocket(SERVER_PORT);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					new Thread(new HandleRequestTask(socket)).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			listener.close();
		}
	}

}
