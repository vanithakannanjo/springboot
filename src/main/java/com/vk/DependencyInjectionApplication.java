package com.vk;

import com.vk.di.UserApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);
		UserApp app = context.getBean(UserApp.class);
		app.loadUserFeeds();

	}

}
