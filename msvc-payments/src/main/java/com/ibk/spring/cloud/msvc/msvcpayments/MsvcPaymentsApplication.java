package com.ibk.spring.cloud.msvc.msvcpayments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcPaymentsApplication.class, args);
	}

}
