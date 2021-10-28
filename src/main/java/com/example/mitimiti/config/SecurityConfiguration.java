package com.example.mitimiti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
				.and().formLogin().loginPage("/login").loginProcessingUrl("/auth")
				.usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/user").failureUrl("/login?error")
				.and().csrf()
					.disable();
	}
}