package com.ibk.spring.cloud.msvc.msvcbalances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcBalancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcBalancesApplication.class, args);
	}

}
