package com.expect.csip;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: jack.zeng
 * @date:2018年2月15日 上午11:54:44
 */
@SpringBootApplication(scanBasePackages = {"com.expect"})
@EnableTransactionManagement //开启声名式事务
//@EnableScheduling
@EnableAsync
@EnableSwagger2Doc
public class Starter {


	/**
	 * @Title: main
	 * @Description: 系统启动方法
	 * @param args 参数
	 */
	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
}
