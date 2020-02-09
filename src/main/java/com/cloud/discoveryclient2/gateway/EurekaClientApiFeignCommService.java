package com.cloud.discoveryclient2.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("greetingClient")
public interface EurekaClientApiFeignCommService {

	@RequestMapping(method=RequestMethod.GET,value="/api/v1/greet")
	public String getMessage();
	
}
