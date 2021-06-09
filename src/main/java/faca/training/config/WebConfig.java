package faca.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {// allowed all website contac to get API
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}
}
