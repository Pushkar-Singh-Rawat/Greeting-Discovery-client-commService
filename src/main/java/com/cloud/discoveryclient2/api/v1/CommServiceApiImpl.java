package com.cloud.discoveryclient2.api.v1;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/api/v1")
@Controller
public class CommServiceApiImpl implements CommServiceApi{
    @Value("${greeting.message}")
    private String msg;
    @Value("${external.services.greetingClientService.name}")
    private String serviceName;
    @Value("${external.services.greetingClientService.path}")
    private String servicePath;
	@Autowired
	DiscoveryClient client;
	
	@GetMapping("/fullmessage")
	@Override
	public String getFullWelcomeText() {
		// TODO Auto-generated method stub
		
		return getGreetingMessage(serviceName)+msg;
	}
	
	public String getGreetingMessage(String serviceName) {
		List<ServiceInstance> service=client.getInstances(serviceName);
		if(!StringUtils.isEmpty(service)) {
			URI uri=service.get(0).getUri();
			String host=uri.getHost();
			int port=uri.getPort();
			String pathValue = null;
			if(serviceName.equalsIgnoreCase("greetingClient"))
				pathValue=servicePath;
			UriComponents uriComponent=UriComponentsBuilder.newInstance().scheme("http")
					.host(host).port(port).path(pathValue).build();
			
			if(!StringUtils.isEmpty(uri)) {
				return (new RestTemplate()).getForObject(uriComponent.toUri(), String.class);
			}
		}
			
		return null;
	}
	
	
}
