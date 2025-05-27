package com.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/auth/register").permitAll();
        http.authorizeHttpRequests().requestMatchers("/course").permitAll();
        http.authorizeHttpRequests().requestMatchers("/courses/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/course/delete").permitAll();
        http.authorizeHttpRequests().requestMatchers("/course/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/lessons").permitAll();
        http.authorizeHttpRequests().requestMatchers("/lessons/**").permitAll();
        //bất kỳ req nào cũng cần
        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.httpBasic().and().build();
    }
}
