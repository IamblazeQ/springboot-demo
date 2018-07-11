package com.eshore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//无数据库配置，补充exclude={DataSourceAutoConfiguration.class}
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableCaching
public class SpringbootDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringbootDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}



}
