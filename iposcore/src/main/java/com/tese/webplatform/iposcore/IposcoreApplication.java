package com.tese.webplatform.iposcore;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

// import com.tese.webplatform.iposcore.repositories.HelloService;
// import com.tese.webplatform.iposcore.repositories.HelloServiceFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class IposcoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(IposcoreApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {

            @Override
			public void addCorsMappings(CorsRegistry resg){
				
                resg.addMapping("/**").
                allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").
                allowedHeaders("*").
                allowedOrigins("*");
			}
		};
	}

	@PostConstruct
	public void started(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
