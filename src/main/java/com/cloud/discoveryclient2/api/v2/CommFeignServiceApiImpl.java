package com.cloud.discoveryclient2.api.v2;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.discoveryclient2.api.v1.CommServiceApi;
import com.cloud.discoveryclient2.gateway.EurekaClientApiFeignCommService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rx.Observable;
//comms with eurekaclient service using feign
@RequestMapping("/api/v2")
@Controller
public class CommFeignServiceApiImpl implements CommServiceApi {
	@Value("${greeting.message}")
    private String msg;
	
	@Autowired
	EurekaClientApiFeignCommService feignService;
	@HystrixCommand(fallbackMethod = "getFallbackText")
	@GetMapping("/fullmessage")
	@Override
	public String getFullWelcomeText() {
		// TODO Auto-generated method stub
		       //ArithmeticException knowingly thrown to test circuit breaker.s
		      // System.out.println(1/0);
				return feignService.getMessage()+msg;
			}

	public String getFallbackText(Throwable e) {
		return "Welcome"+e+"exception";
	}
}
