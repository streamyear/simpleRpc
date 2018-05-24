package com.rpc.client;

import com.rpc.common.bean.User;
import com.rpc.common.service.UserService;

public class RpcClient {
	public static void main(String[] args) {
		UserService userService = (UserService)RpcClientProxy.create(UserService.class);
		User zhangsan = userService.getUserByUserName("zhangsan");
		System.out.println(zhangsan);
	}
}
