package com.cloud.discoveryclient2.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloud.discoveryclient2.gateway.config.ConfigureFeign;

@FeignClient(name="gateway",configuration=ConfigureFeign.class)
public interface EurekaClientApiFeignCommService {

	@RequestMapping(method=RequestMethod.GET,value="greetingMessage/api/v1/greet")
	public String getMessage();
	
}
