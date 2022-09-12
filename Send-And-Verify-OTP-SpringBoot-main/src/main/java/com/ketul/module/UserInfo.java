package com.ketul.module;

public class UserInfo {
	
	private String phoneNumber;

	public UserInfo() {
		super();
	}

	public UserInfo(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "UserInfo [phoneNumber=" + phoneNumber + "]";
	}
}
