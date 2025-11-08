package com.abishek.ecommercewebsiteapiproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private UserDetailsService userdetailsservice;

    private JwtFilter jwtFilter;
	
	public SecurityConfiguration(UserDetailsService userdetailsservice,JwtFilter jwtFilter) {
		super();
		this.userdetailsservice = userdetailsservice;
        this.jwtFilter = jwtFilter;
	}


	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpsecurity) throws Exception {
		
		httpsecurity.csrf(customizer->customizer.disable());//disabling csrftoken
		//authenticate all http requests
		httpsecurity.authorizeHttpRequests(request->request.requestMatchers("/laptopstore/api/v1/register").
				permitAll().anyRequest().authenticated());
		//add a default form validation
		httpsecurity.httpBasic(Customizer.withDefaults());
		//To make the api stateless
		httpsecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpsecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return httpsecurity.build();	
		
	}
	
	
	@Bean
	public AuthenticationProvider authprovider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userdetailsservice);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		
		return provider;
		
	}
	
	@Bean(name="bcryptpasswordencoder")
	public BCryptPasswordEncoder getbcryptpasswordencoder() {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
		return bcrypt;
	}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

       return config.getAuthenticationManager();
    }



}
