package com.txr.spbcacheehcache;

import org.springframework.stereotype.Service;

/**
 * Created by xinrui.tian on 2018/11/13.
 */
@Service
public class BondCacheService extends EhCacheBase {

/*    public List<String> getAllBondKeys () {
        testLocalCache();
        return BondAccess.getAllBond().stream().map(Bond::getBondKey).collect(Collectors.toList());
    }*/

    /**
        启用缓存  @EnableCaching  +  @Cacheable

        @Cacheable(value = EhCacheConfig.EH_CACHE_KEY, key = "#bondKey")
           》 spring boot 自带缓存
           》 如果指定 spring.cache.ehcache.component=classpath:ehcache-Template.xml 并配置 ehcache-Template.xml 则使用 ehcache

        @Cacheable(value = EhCacheConfig.EH_CACHE_KEY, cacheManager = "ehCacheManager", key = "#bondKey")  //使用CacheManager
            》ehcache >>>> cacheManager = "ehCacheManager" 对应的是EhCacheConfig Bean 的方法名
     */

  /*  @Cacheable(value = CacheConfig.EH_CACHE_KEY, key = "#bondKey")
    public List<Bond> findBondByBondKey (String bondKey) throws BondNotFoundException {
        List<Bond> result = BondAccess.getAllBond().stream().filter(x -> x.getBondKey().equals(bondKey)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(result)) {
            throw new BondNotFoundException(bondKey);
        }
        return result;
    }*/

}
