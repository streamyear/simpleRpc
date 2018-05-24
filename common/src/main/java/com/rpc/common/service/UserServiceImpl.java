package com.rpc.common.service;

import com.rpc.common.bean.User;

public class UserServiceImpl implements UserService{

	@Override
	public User getUserByUserName(String userName){
		System.out.println("传入的参数是: " + userName);
		User user = new User("beck", "小石头");
		return user;
	}
}
