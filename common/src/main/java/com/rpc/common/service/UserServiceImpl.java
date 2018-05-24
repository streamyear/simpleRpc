package com.rpc.common.service;

import com.rpc.common.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserServiceImpl implements UserService{

	@Override
	public User getUserByUserName(String userName){
		System.out.println("传入的参数是: " + userName);
		List<User> usersList = generateUserList();
		int index = new Random().nextInt(usersList.size());
		return usersList.get(index);
	}

	/**
	 * 产生10个user
	 */
	private List<User> generateUserList(){
		List<User> result = new ArrayList<>();
		for (int i = 0; i < 10; i++){
			User user = new User("stream" + i, "流年" + i);
			result.add(user);
		}
		return result;
	}
}
