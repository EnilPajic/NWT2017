package com.enil.haberservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableDiscoveryClient
public class HaberServicesApplication {

	@RequestMapping("/")
	public String Index()
	{
		return "<h1>Home - services</h1>";
	}

	@Autowired
	DataSource ds;

	public static void main(String[] args) {
		SpringApplication.run(HaberServicesApplication.class, args);
	}
}
