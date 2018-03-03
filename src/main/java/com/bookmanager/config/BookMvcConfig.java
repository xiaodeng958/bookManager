package com.bookmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class BookMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
//		registry.addViewController("/bookList").setViewName("bookList");
//		registry.addViewController("/tttt").setViewName("tttt");
//		registry.addViewController("/ssss").setViewName("ssss");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

//		registry.addResourceHandler("static/**").addResourceLocations("classpath:/static/");
	}
}
