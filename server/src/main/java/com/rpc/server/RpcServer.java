package com.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {
	public static final int SERVER_PORT = 10000;
	private static ExecutorService executorService = Executors.newFixedThreadPool(5);

	public static void main(String[] args) throws IOException {

		new RpcServer().run();
	}

	private void run() throws IOException {
		ServerSocket listener = new ServerSocket(SERVER_PORT);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					executorService.execute(new HandleRequestTask(socket));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			listener.close();
		}
	}

}
