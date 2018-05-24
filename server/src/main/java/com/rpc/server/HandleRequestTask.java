package com.rpc.server;

import com.rpc.common.bean.RpcRequest;
import com.rpc.common.bean.RpcResponse;
import com.rpc.common.service.UserService;
import com.rpc.common.service.UserServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理请求的任务
 */
public class HandleRequestTask implements Runnable{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

	private Socket socket;

	public HandleRequestTask(Socket socket){
		this.socket = socket;
	}

	public void run() {
		try {
			// 将请求反序列化
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			RpcRequest request = (RpcRequest)objectInputStream.readObject();

			System.out.println(sdf.format(new Date()) + " request is: " + request);
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
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
