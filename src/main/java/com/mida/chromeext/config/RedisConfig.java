package com.mida.chromeext.config;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 类RedisConfig的功能描述:
 * Redis配置
 *
 * @auther hxy
 * @date 2017-11-15 21:49:31
 */
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private static Logger logger = Logger.getLogger(RedisConfig.class);

    private String host;

    private int port;

    private String password;

    private int timeout;

    @Bean
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = getRedisConfig();
        JedisPool pool;
        if (StringUtils.isEmpty(password)) {
            pool = new JedisPool(config, host, port, timeout);
        } else {
            pool = new JedisPool(config, host, port, timeout, password);
        }
        logger.info("init JredisPool ...");
        return pool;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String hostName) {
        this.host = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
