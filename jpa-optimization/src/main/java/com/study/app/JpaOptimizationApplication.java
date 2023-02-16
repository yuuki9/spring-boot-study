package com.study.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Clock;

@SpringBootApplication
public class JpaOptimizationApplication {
	
	public static void main(String[] args) {
		System.out.println("체크");
		SpringApplication.run(JpaOptimizationApplication.class, args);
	}

}
