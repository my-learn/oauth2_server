package com.dotnar.usc.config;

import com.dotnar.usc.core.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

//这个类用配置redis服务器的连接(注意，redisNamespace必须填写不一样的值，否则同一个redis用于不同的系统时候，会取到别人的id，切记！！！)
//默认的就是1800秒，所以这里会提示
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800, redisNamespace = "com.dotnar.usc.oauth_server")
public class SessionConfig {

	@Autowired
	RedisConfig redisConfig;

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
    	return new CookieHttpSessionStrategy();
    }

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connection = new JedisConnectionFactory();
		connection.setPort(redisConfig.getPort());
		connection.setHostName(redisConfig.getHost());
		connection.setPassword(redisConfig.getPassword());
		return connection;
	}
}