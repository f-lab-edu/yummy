package com.huijin.yummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication/*(exclude={DataSourceAutoConfiguration.class})*/
public class YummyApplication {

	public static void main(String[] args) {
		SpringApplication.run(YummyApplication.class, args);
	}

}
