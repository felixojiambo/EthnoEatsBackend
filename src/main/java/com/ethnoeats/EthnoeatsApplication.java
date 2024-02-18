package com.ethnoeats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class EthnoeatsApplication {

	public static void main(String[] args) {

		SpringApplication.run(EthnoeatsApplication.class, args);
		System.out.println("Java & Angular");
	}

}
