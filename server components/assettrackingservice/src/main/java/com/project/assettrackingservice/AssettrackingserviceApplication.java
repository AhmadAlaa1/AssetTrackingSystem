package com.project.assettrackingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AssettrackingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssettrackingserviceApplication.class, args);
	}

}
