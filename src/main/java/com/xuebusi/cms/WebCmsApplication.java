package com.xuebusi.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xuebusi.cms.api.mapper")
public class WebCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCmsApplication.class, args);
	}

}
