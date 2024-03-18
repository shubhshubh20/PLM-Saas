package com.saas.plm.plm_web;

import com.saas.plm.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.saas.plm.*"})
@EnableJpaRepositories({"com.saas.plm.repository"})
@EntityScan("com.saas.plm.model")
public class PlmWebApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PlmWebApplication.class, args);
		String[] beanNames = context.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}


}
