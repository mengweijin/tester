package com.mengweijin.tester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mengweijin
 */
@SpringBootApplication(scanBasePackages = {"com.mengweijin"})
public class TesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesterApplication.class, args);
    }

}
