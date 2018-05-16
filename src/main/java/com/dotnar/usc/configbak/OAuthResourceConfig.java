//package com.dotnar.usc.configbak;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.configbak.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.configbak.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.configbak.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.configbak.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//import static com.dotnar.usc.configbak.OAuthAuthorizationConfig.RESOURCE_ID;
//
//@Configuration
//@EnableResourceServer
//public class OAuthResourceConfig extends ResourceServerConfigurerAdapter {
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId(RESOURCE_ID);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
//                .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')");
//    }
//}