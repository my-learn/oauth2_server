package com.dotnar.usc.config;

import com.dotnar.usc.common.util.EncryptUtils;
import com.dotnar.usc.provider.CustomAuthenticationProvider;
import com.dotnar.usc.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SimpleAuthenticationFilter filter;

    @Override//原始登录方式配置
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers()
                .antMatchers(
                        "/"
                        , "/login"
                        , "/oauth/token"
                        , "/oauth/authorize"
                )
                .and()
                .authorizeRequests().antMatchers("/login**").permitAll()//其中login**代表后面所有参数都权限放开
                .and()
                .formLogin().permitAll().loginPage("/login").failureUrl("/login?error=true")
                .and()
                .authorizeRequests().anyRequest().authenticated()
        ;
        http.addFilterBefore(filter, SecurityContextPersistenceFilter.class);
    } // @formatter:on

//    @Override//原始登录方式配置
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .requestMatchers()
//                .antMatchers(
//                        "/"
//                        ,"/login"
//                        ,"/oauth/authorize"
//                )//这是必须有的配置，否则Access is denied (user is anonymous); redirecting to authentication entry point和org.springframework.security.access.AccessDeniedException: Access is denied
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//        ;
//    } // @formatter:on

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
//        auth
//            .inMemoryAuthentication()
//            .withUser("john")
//            .password("123")
//            .roles("USER");
//    } // @formatter:on


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/style/**", "/lib/**", "/js/**", "/img/**");//放开静态资源
    }

    // @formatter:off
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    // @formatter:on


    @Bean
    public UserDetailsServiceImpl customUserService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return EncryptUtils.encryptMD5(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        };
//        return new Md5PasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }
}
