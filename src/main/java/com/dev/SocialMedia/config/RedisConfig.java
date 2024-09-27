package com.dev.SocialMedia.config;

import com.dev.SocialMedia.common.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String hostName;
    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory(
                new RedisStandaloneConfiguration(hostName, port)
        );
    }

    // cache response
    @Bean
    public RedisTemplate<String, ApiResponse> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ApiResponse> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<ApiResponse> serializer = new Jackson2JsonRedisSerializer<>(ApiResponse.class);
        template.setValueSerializer(serializer);
        return template;
    }

    // set as default cache manager
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.create(connectionFactory);
    }

}
