package com.enil.haberconf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.lang.reflect.Field;

@EnableConfigServer
@SpringBootApplication
public class HaberConfApplication {
	static {
		try {
			Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
			field.setAccessible(true);
			field.set(null, java.lang.Boolean.FALSE);
		} catch (Exception ex) {
		}
	}
	public static void main(String[] args) {

		SpringApplication.run(HaberConfApplication.class, args);
	}
}
