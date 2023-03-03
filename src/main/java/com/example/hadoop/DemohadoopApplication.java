package com.example.hadoop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@MapperScan("com.eyiadmin.demo.mapper")
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.example.hadoop.dao")
public class DemohadoopApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(DemohadoopApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
