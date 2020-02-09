package com.cloud.discoveryclient2.api.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.discoveryclient2.api.v1.CommServiceApi;
import com.cloud.discoveryclient2.gateway.EurekaClientApiFeignCommService;
//comms with eurekaclient service using feign
@RequestMapping("/api/v2")
@Controller
public class CommFeignServiceApiImpl implements CommServiceApi {
	@Value("${greeting.message}")
    private String msg;
	
	@Autowired
	EurekaClientApiFeignCommService feignService;
	@GetMapping("/fullmessage")
	@Override
	public String getFullWelcomeText() {
		// TODO Auto-generated method stub
		return feignService.getMessage()+msg;
	}

}
