package com.rpc.client;

import com.rpc.common.bean.User;
import com.rpc.common.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RpcClient {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

	public static void main(String[] args) {
		UserService userService = RpcClientProxy.create(UserService.class);

		for (int i = 1; i < 10; i++){
			User zhangsan = userService.getUserByUserName("zhangsan");
			System.out.println(sdf.format(new Date()) + " " + zhangsan);
		}
	}
}
