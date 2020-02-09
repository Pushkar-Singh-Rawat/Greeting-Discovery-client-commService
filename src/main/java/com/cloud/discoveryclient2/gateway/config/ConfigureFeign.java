package com.cloud.discoveryclient2.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class ConfigureFeign {
    //a basic retryer, by implementing the Retryer interface a more customized
	//retryer can be created.
	@Bean
	public Retryer retryer() {
		return new Retryer.Default(2000,10000,3);
	}
}
