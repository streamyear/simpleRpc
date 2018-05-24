package com.rpc.common.service;

import com.rpc.common.bean.User;

public interface UserService {
	User getUserByUserName(String userName);
}
