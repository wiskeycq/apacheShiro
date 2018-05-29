package com.cq.apacheShiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan
@MapperScan(basePackages = {"com.cq.apacheShiro.mapper"})//通过使用@MapperScan可以指定要扫描的Mapper类的包的路径;之前是，直接在Mapper类上面添加注解@Mapper，这种方式要求每一个mapper类都需要添加此注解，麻烦。
@SpringBootApplication
public class ApacheShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheShiroApplication.class, args);
	}
}
