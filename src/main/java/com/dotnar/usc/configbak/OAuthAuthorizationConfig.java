//package com.dotnar.usc.configbak;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.configbak.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.configbak.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.configbak.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.configbak.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
//@Configuration
//@EnableAuthorizationServer
//public class OAuthAuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//
//    public static final String OAUTH_CLIENT_ID = "client";
//    public static final String OAUTH_CLIENT_SECRET = "secret";
//    public static final String[] SCOPES = { "read", "write" };
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient(OAUTH_CLIENT_ID)
//                .secret(OAUTH_CLIENT_SECRET)
//                .scopes(SCOPES)
//                .autoApprove(true)
//                .authorities("ROLE_USER")
//                .authorizedGrantTypes("authorization_code", "refresh_token","implicit")
//                .accessTokenValiditySeconds(60*30) // 30min
//                .refreshTokenValiditySeconds(60*60*24) // 24h
//        ;
//    }
//
//    @Override
//    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager);
//    }
//}