package edu.sjsu.cmpe275.lab2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "edu.sjsu.cmpe275.lab2")
public class ApplicationConfig {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewRes = new InternalResourceViewResolver();
		viewRes.setViewClass(JstlView.class);
		viewRes.setPrefix("/WEB-INF/views/");
		viewRes.setSuffix(".jsp");

		return viewRes;
	}

}
