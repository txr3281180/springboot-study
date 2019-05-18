package com.txr.spbbasic.global.config.cache;


import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisConfig {

    /*spring boot 会自动映射 redis 配置信息*/

    /*
        RedisTemplate与StringRedisTemplate的区别:
            1.两者数据各自存，各自取，数据不互通。
                > RedisTemplate不能取StringRedisTemplate存入的数据
                > StringRedisTemplate不能取RedisTemplate存入的数据
            2.序列化策略不同
                > RedisTemplate采用JDK的序列化策略
                > StringRedisTemplate采用String的序列化策略
    */

    //RedisTemplate 存入数据在管理界面无法查看
    //配置 StringRedisTemplate
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        return template;
    }


//==========================================================================================
/*
    //spring boot 1.5 配置方案
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        return redisCacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
*/
//==========================================================================================
    //RedisMessage    reids实体类消息 topic
/*    @Bean
    @Qualifier("redisTemplate")
    RedisTemplate<String, RedisMessage> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, RedisMessage> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<RedisMessage> jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<RedisMessage> serializer = new Jackson2JsonRedisSerializer<>(RedisMessage.class);
        serializer.setObjectMapper(objectMapper());
        return serializer;
    }


    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }*/
}
