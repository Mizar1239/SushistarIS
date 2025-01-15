package com.Sushistar.SushistarIS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class SushistarIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SushistarIsApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Arrays.asList(allowed_origins));
		config.setAllowedHeaders(Arrays.asList(allowed_headers));
		config.setExposedHeaders(Arrays.asList(exposed_headers));
		config.setAllowedMethods(Arrays.asList(allowed_methods));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

	private final String[] allowed_origins = {
			"http://localhost:4200",
	};

	private final String[] allowed_methods = {
			"GET",
			"POST",
			"PUT",
			"DELETE",
			"OPTIONS"
	};

	private final String[] allowed_headers = {
			"Origin",
			"Access-Control-Allow-Origin",
			"Content-Type",
			"Accept",
			"Authorization",
			"Origin, Accept",
			"X-requested-with",
			"Access-Control-Request-Method",
			"Access-Control-Request-Headers",
	};

	private final String[] exposed_headers = {
			"Origin",
			"Content-Type",
			"Accept",
			"Authorization",
			"Access-Control-Allow-Origin",
			"Access-Control-Allow-Origin",
			"Access-Control-Allow-Credentials"
	};
}
