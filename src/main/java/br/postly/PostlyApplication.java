package br.postly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostlyApplication.class, args);
	}

}
