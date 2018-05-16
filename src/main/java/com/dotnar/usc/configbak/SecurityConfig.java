//package com.dotnar.usc.configbak;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.configbak.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.configbak.annotation.web.builders.HttpSecurity;
//import org.springframework.security.configbak.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.configbak.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.parentAuthenticationManager(authenticationManager)
//                .inMemoryAuthentication()
//                .withUser("user").password("123").roles("USER")
//                .and()
//                .withUser("admin").password("123").roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
////                .antMatchers("/css/**", "/index", "/login-error", "/u", "/v", "/error", "/testLogin", "/isLogin").permitAll()
////                .antMatchers("/user/**").hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout()
//                .permitAll()
//        ;
//    }
//}