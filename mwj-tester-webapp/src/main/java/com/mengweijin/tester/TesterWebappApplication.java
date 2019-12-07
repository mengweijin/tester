package com.mengweijin.tester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mengweijin
 */
@SpringBootApplication(scanBasePackages = {"com.mengweijin.tester"})
public class TesterWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesterWebappApplication.class, args);
	}

}
