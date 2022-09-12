package com.ketul.module;

public class VerifyUser {
	
	private String phoneNumber;
	private int otp;
	
	public VerifyUser() {
		super();
	}
	
	public VerifyUser(String phoneNumber, int otp) {
		super();
		this.phoneNumber = phoneNumber;
		this.otp = otp;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	@Override
	public String toString() {
		return "VerifyUser [phoneNumber=" + phoneNumber + ", otp=" + otp + "]";
	}
}
