package com.enil.haberusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableDiscoveryClient
public class HaberUsersApplication {

	@RequestMapping ("/")
	public String Index()
	{
		return "<h1>Home</h1>";
	}
	public static void main(String[] args) {
		SpringApplication.run(HaberUsersApplication.class, args);
	}
}
