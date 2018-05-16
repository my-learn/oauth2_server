package com.dotnar.usc;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.dotnar.usc.core.util.FastDFSFileUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = {"com.dotnar.usc"},excludeFilters = {@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,value=FastDFSFileUtil.class)})
@MapperScan("com.dotnar.usc.core.mapper")
@SpringBootApplication(
        exclude = {
//                FastDFSFileUtil.class
//        ,DataSourceAutoConfiguration.class
//        , DruidDataSourceAutoConfigure.class
//        , RedisAutoConfiguration.class//解决不需要引用redis时候的异常
//        , RedisRepositoriesAutoConfiguration.class//解决没有redisTemplate没找到的异常：A component required a bean named 'redisTemplate' that could not be found.
}
)
@EnableResourceServer
public class OAuth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServerApplication.class, args);
    }

}