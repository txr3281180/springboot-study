package com.txr.spbbasic.service;

import com.txr.spbbasic.global.config.cache.CacheConfig;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/1/7
 */
public class EhCacheBase {

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    public Ehcache getEhCache(String cacheName) {
        // Cache -> EhCacheCache -> Ehcache
        return ((EhCacheCache)ehCacheCacheManager.getCache(cacheName)).getNativeCache();
    }

    public Cache getCache(String cacheName) {
        return ehCacheCacheManager.getCache(cacheName);
    }

    public void testLocalCache () {
        Collection<String> cacheNames = ehCacheCacheManager.getCacheNames();

        //Ehcache
        Ehcache ehcache = getEhCache(CacheConfig.EH_CACHE_TEST_KEY);
        ehcache.put(new Element("test1",  "testVal1"));
        ehcache.put(new Element("test2",  "testVal2"));
        ehcache.put(new Element("test3",  "testVal3"));

        String name = ehcache.getName();
        Status status = ehcache.getStatus();
        //Query query = ehcache.createQuery();  //需要配置 searchable

        List keys = ehcache.getKeys();
        Element test3 = ehcache.getQuiet("test3");
        Element element = ehcache.get("test1");
        Map<Object, Element> all = ehcache.getAll(keys);

        ehcache.remove("test1");
        ehcache.flush();
        List keys2 = ehcache.getKeys();


        //Cache
        Cache cache = getCache(CacheConfig.EH_CACHE_TEST_KEY);
        cache.put("cache1", "cacheVal1");
        cache.put("cache2", "cacheVal2");
        cache.put("cache3", "cacheVal3");

        Cache.ValueWrapper cache1 = cache.get("cache1");
        Object o = cache1.get();

        String name1 = cache.getName();
        cache.evict("cache1");

        Cache.ValueWrapper cache2 = cache.get("cache1");
        //Object o1 = cache2.get();  //空指针
        cache.clear();
    }
}
