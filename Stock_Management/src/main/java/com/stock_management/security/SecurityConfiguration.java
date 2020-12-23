//package com.stock_management.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@SuppressWarnings("deprecation")
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("Awad").password("29234280").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("Hanaa").password("12345678").roles("CASHIER");
//    }
//
////    security for all apis
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
////        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
////    }
//
////    security based on url
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
////        http.authorizeRequests().antMatchers("/product/**").fullyAuthenticated().and().httpBasic();
////    }
//
////    security based on role
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
////        http.authorizeRequests().antMatchers("/product/**").hasAnyRole("ADMIN").anyRequest().fullyAuthenticated().and().httpBasic();
////    }
//
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
//}
