package com.centime.concatenate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; 

@SpringBootApplication 
@EnableDiscoveryClient
public class ConcatenateApp {

    public static void main(String[] args) {
        SpringApplication.run(ConcatenateApp.class, args);
    }
}
