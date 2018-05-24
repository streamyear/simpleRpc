package com.rpc.server;

import com.rpc.common.bean.RpcRequest;
import com.rpc.common.bean.RpcResponse;
import com.rpc.common.service.UserService;
import com.rpc.common.service.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcServer {
	public static void main(String[] args) throws IOException {
		new RpcServer().run();
	}

	private void run() throws IOException {
		ServerSocket listener = new ServerSocket(10000);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					// 将请求反序列化
					ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
					RpcRequest request = (RpcRequest)objectInputStream.readObject();

					System.out.println("request is: " + request);
					// 调用服务
					UserService userService = new UserServiceImpl();
					Class clz = userService.getClass();
					Method method = clz.getMethod(request.getMethodName(), request.getParameterTypes());
					Object result = method.invoke(userService, request.getParameters());

					// 返回结果
					RpcResponse rpcResponse = new RpcResponse();
					rpcResponse.setResult(result);
					rpcResponse.setRequestId(request.getRequestId());
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(rpcResponse);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}

}
