package com.chaiyot.javaspringmoviecollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.chaiyot.javaspringmoviecollection.model.*;
@SpringBootApplication
public class JavaspringmoviecollectionApplication implements WebMvcConfigurer{

	public static void main(String[] args)  {
		SpringApplication.run(JavaspringmoviecollectionApplication.class, args);
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		// List เอาไว้ยกเว้นการเช็ค session
		String[] allExcludePath  = {"/login","/css/**","/js/**","/img/**","/admin/logout"};
	
        registry.addInterceptor(new SessionInterceptor())
        .addPathPatterns("/admin/**")
        .excludePathPatterns(allExcludePath);
    }

}
