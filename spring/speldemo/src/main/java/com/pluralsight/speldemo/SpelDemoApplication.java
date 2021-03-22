package com.pluralsight.speldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpelDemoApplication {



    public static void main(String[] args) {
        System.out.println(String.join(", ", args));
        SpringApplication.run(SpelDemoApplication.class, args);
    }

}
