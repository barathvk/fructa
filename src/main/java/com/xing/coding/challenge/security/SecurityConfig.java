package com.xing.coding.challenge.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.csrf().disable().authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers(HttpMethod.POST, "/login").permitAll()
//      .antMatchers("/api/admin/**").hasRole("ADMIN")
      .antMatchers("/**").authenticated()
      .anyRequest().permitAll()
      .and()
      .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("admin")
      .password("password")
      .roles("ADMIN");
    auth.inMemoryAuthentication()
      .withUser("player1")
      .password("password")
      .roles("USER");
    auth.inMemoryAuthentication()
      .withUser("player2")
      .password("password")
      .roles("USER");
    auth.inMemoryAuthentication()
      .withUser("player2")
      .password("password")
      .roles("USER");
  }
}
