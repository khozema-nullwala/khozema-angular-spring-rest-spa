package net.kzn.collaboration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"net.kzn.collaboration.controller"})
public class MvcConfig extends WebMvcConfigurerAdapter {
	// Configuration to load the static resources	
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/app/**").addResourceLocations("/app/");
	    registry.addResourceHandler("/resources/**").addResourceLocations("/assets/");
	 }
	 
	 @Bean
	 public ViewResolver configureViewResolver() {
	     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	     viewResolver.setPrefix("/WEB-INF/");
	     viewResolver.setSuffix(".html");
	     return viewResolver;
	 }	 
	 
	 @Override
	 public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
	   configurer.enable();
	 }	 
}
