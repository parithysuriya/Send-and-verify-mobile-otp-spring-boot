package com.ketul.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ketul.module.TwilioInfo;
import com.ketul.module.UserInfo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OtpService {

	@Autowired
	TwilioInfo twilioInfo;

	@PostConstruct
	public void initTwilio() {
		Twilio.init(twilioInfo.getACCOUNT_SID(), twilioInfo.getAUTH_TOKEN());

		System.out.println("Yes" + twilioInfo.getACCOUNT_SID() + " " + twilioInfo.getAUTH_TOKEN());
	}

	private static final Integer EXPIRE_MINS = 5;

	private LoadingCache<String, Integer> otpCache;

	public OtpService() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public int generateOTP(String key) {

		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}

	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}

	public void sendSms(@Valid UserInfo userInfo) {
		int number = generateOTP(userInfo.getPhoneNumber());
		
		String msg ="Your OTP - " + number;
        
	       

        Message message = Message
                .creator(new PhoneNumber(userInfo.getPhoneNumber()),
                        new PhoneNumber(twilioInfo.getPHONE_NUMBER()),
                        msg)
                .create();
	}
}
