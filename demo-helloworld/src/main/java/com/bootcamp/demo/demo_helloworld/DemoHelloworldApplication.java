package com.bootcamp.demo.demo_helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoHelloworldApplication {

	// Trigger Point -> Start Server -> run main()
	// the programme runs forever
	public static void main(String[] args) {
		SpringApplication.run(DemoHelloworldApplication.class, args);
	}

}
