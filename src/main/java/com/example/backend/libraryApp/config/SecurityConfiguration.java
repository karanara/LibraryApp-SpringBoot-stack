package com.example.backend.libraryApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.okta.spring.boot.oauth.Okta;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf((csrf)->csrf.disable());
		http.authorizeHttpRequests(configurer->
	    configurer.requestMatchers("/api/books/secure/**")
	             .authenticated()).oauth2ResourceServer(oauth2->oauth2.jwt());
		http.cors(withDefaults());
		http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
		Okta.configureResourceServer401ResponseBody(http);
		http.build();
		return null;
		
	}
}
