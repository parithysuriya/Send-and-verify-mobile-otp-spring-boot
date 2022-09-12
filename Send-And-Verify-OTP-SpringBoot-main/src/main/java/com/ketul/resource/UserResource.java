package com.ketul.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ketul.module.UserInfo;
import com.ketul.module.VerifyUser;
import com.ketul.service.OtpService;

@RestController
public class UserResource {
	
	@Autowired
	private OtpService otpService;

	@PostMapping("/send")
	public void sendSms(@Valid @RequestBody UserInfo userInfo) {
		otpService.sendSms(userInfo);
	}
	
	@PostMapping("/verify")
	public String varifyOtp(@Valid @RequestBody VerifyUser veryfyUser) {
		Integer generatedOtp = otpService.getOtp(veryfyUser.getPhoneNumber());
	
		
		if(veryfyUser.getOtp() == generatedOtp) {
			otpService.clearOTP(veryfyUser.getPhoneNumber());
			return "Correct Otp";
		}
		
		return "Not Correct OTP";
	}
}
