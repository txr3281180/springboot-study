package com.txr.spbcacheredis.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableCaching  //缓存注解
@Import(value = {  //导入配置类
            RedisConfig.class
        })
public class CacheConfig {

    /**
     * @Primary 在众多相同的bean中，优先选择用@Primary注解的bean（该注解加在各个bean上）
     * @Qualifier 在众多相同的bean中，@Qualifier指定需要注入的bean（该注解跟随在@Autowired后）
     */

    /* 本地缓存 1.Ehcache  2.JCache  3.Caffeine
        >JCache    javax.cache.CacheManager
        >Caffeine  com.github.benmanes.caffeine.cache.Caffeine
            Caffeine是使用Java8对Guava缓存的重写版本,在Spring 5.0或者Spring Boot 2.0中将取代，基于LRU算法实现，支持多种缓存过期策略
        >Ehcache 支持 xml 与 bean 两种配置
     */

    public static final String REDIS_KEY = "sp:redis:";

    public static final String EH_CACHE_KEY = "bond_cache";
    public static final String EH_CACHE_TEST_KEY = "TEST";
}
