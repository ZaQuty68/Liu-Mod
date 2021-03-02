package com.example.LiuMod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiuModApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(LiuModApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}
	Liu liu = new Liu("graph1.txt");
}
