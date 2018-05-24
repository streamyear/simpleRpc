package com.rpc.common.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	private String userName;

	private String nickName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public User() {
	}

	public User(String userName, String nickName) {

		this.userName = userName;
		this.nickName = nickName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;

		return Objects.equals(userName, user.userName) &&
				Objects.equals(nickName, user.nickName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, nickName);
	}

	@Override
	public String toString() {
		return "User{" +
				"userName='" + userName + '\'' +
				", nickName='" + nickName + '\'' +
				'}';
	}
}
