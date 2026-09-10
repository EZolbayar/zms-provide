package com.example.terguun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TerguunApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerguunApplication.class, args);
	}

}
