package com.rpc.client;

import com.rpc.common.bean.RpcRequest;
import com.rpc.common.bean.RpcResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.UUID;

public class RpcClientProxy {
	private static final String ADDRESS = "127.0.0.1";
	private static final int PORT = 10000;

	@SuppressWarnings("unchecked")
	public static <T> T create(Class<?> interfaceClass){
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class<?>[]{interfaceClass}, new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						try {
							RpcRequest rpcRequest = new RpcRequest();
							rpcRequest.setRequestId(UUID.randomUUID().toString());
							rpcRequest.setClassName(method.getDeclaringClass().getName());
							rpcRequest.setMethodName(method.getName());
							rpcRequest.setParameterTypes(method.getParameterTypes());
							rpcRequest.setParameters(args);

							Socket socket = new Socket(ADDRESS, PORT);

							// 将请求序列化
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

							// 将请求发给服务提供方
							objectOutputStream.writeObject(rpcRequest);

							// 将响应体反序列化
							ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
							RpcResponse response = (RpcResponse)objectInputStream.readObject();
							System.out.println(response);
							return response.getResult();

						}catch (Exception e){
							e.printStackTrace();
						}

						return null;

					}
				});
	}
}
