package com.txr.spbcacheehcache.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;


public class EhCacheConfig {

    /** 缓存配置 */
    private Cache createCache(String cacheName, int maxEntries) {
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.name(cacheName)
                //.searchable(new Searchable())   //配置缓存查询
                .maxEntriesLocalHeap(maxEntries)
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                .timeToLiveSeconds(7 * 24 * 60 * 60);
        return new Cache(cacheConfiguration);
    }

    /** 缓存管理器 */
    @Bean
    public CacheManager cacheManager() {
        CacheManager cacheManager = CacheManager.create();
        cacheManager.addCache(createCache(CacheConfig.EH_CACHE_KEY, 200000));
        cacheManager.addCache(createCache(CacheConfig.EH_CACHE_TEST_KEY, 200000));

        //CacheManager cacheManager = CacheManager.create(configuration());
        return cacheManager;
    }

    /** EhCache */
    @Bean
    public EhCacheCacheManager ehCacheManager(CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }

    /**
     *
     * Ecache 中获取的是对象的引用；
     *      使用配置 copyOnRead = true,  copyOnWrite=true（但是性能很慢）
     *      copyOnRead 读取复制:  当从缓存中读取一个元素时，该元素是否是复制的。默认为false。
     *      opyOnWrite 写复制:  当向缓存中添加一个元素时，该元素是否是复制的。默认为false。
     *
     *
     * ehcache配置：
     *  name（必填属性）：缓存区名称，用以区别缓存区，必须唯一。
     *  maxEntriesLocalHeap（必填属性）：设置缓存在本地内存中最大缓存项数量（0 表示无限）。
     *                                  （等效于旧版本中的maxElementsInMemory属性）。
     *                                  在实际使用中，在非分布式部署条件下，无限等效于Integer.MAX_SIZE (2147483647)。
     *                                  在分布式部署条件下，缓存项数量由Terracotta Server Array资源上限决定。
     *  memoryStoreEvictionPolicy：当缓存项达到maxEntriesLocalHeap限制时，剔除缓存项的策略。默认为LRU（Least Recently Used）。
     *                              其他的策略有：FIFO（First In First Out）和LFU（Less Frequently Used）。
     *
     *                              EHCache的几种清空策略：
     *                              FIFO：irst in first out  先进先出，
     *                              LFU：Less Frequently Used 缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
     *                              LRU：Least Recently Used  缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
     *  timeToIdleSeconds：设置一个缓存项在过期前的闲置时间。即一个缓存项在其过期前，两次访问间隔的最大时间。
     *                      仅在缓存项为非永久时有效。0表示不限闲置时间，默认为0。
     *
     *  timeToLiveSeconds：设置一个缓存项在过期前的生存时间。即从缓存项创建到 过期的最大时间。仅在缓存项为非永久时有效。
     *                      0表示不限生存时间，默认为0。
     *
     */

    public Configuration configuration() {
        Configuration configuration = new Configuration()
                .diskStore(new DiskStoreConfiguration().path("d://test")) //临时文件目录
              /*
                .cacheManagerPeerProviderFactory(new FactoryConfiguration<FactoryConfiguration<?>>()
                        .className(RMICacheManagerPeerProviderFactory.class.getName())
                        .properties("peerDiscovery=manual,rmiUrls=//localhost:40004/metaCache|//localhost:40005/metaCache")) //指定除自身之外的网络群体中其他提供同步的主机列表，用“|”分开不同的主机

                .cacheManagerPeerListenerFactory(new FactoryConfiguration<FactoryConfiguration<?>>()
                        .className(RMICacheManagerPeerListenerFactory.class.getName())
                        .properties("port=40004,socketTimeoutMillis=2000"))//配宿主主机配置监听程序
              */
               /*.cache(new CacheConfiguration(CacheConfig.EH_CACHE_KEY, 10000)//缓存名称(必须唯一),maxElements内存最多可以存放的元素的数量
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)//清理机制：LRU最近最少使用 FIFO先进先出 LFU较少使用
                        .timeToIdleSeconds(1000)//元素最大闲置时间
                        .timeToLiveSeconds(2000)//元素最大生存时间
                        .eternal(true)//元素是否永久缓存
                        //.diskExpiryThreadIntervalSeconds(120)//缓存清理时间(默认120秒)
                        //LOCALTEMPSWAP当缓存容量达到上限时，将缓存对象（包含堆和非堆中的）交换到磁盘中
                        //NONE当缓存容量达到上限时，将缓存对象（包含堆和非堆中的）交换到磁盘中
                        //DISTRIBUTED按照_terracotta标签配置的持久化方式执行。非分布式部署时，此选项不可用
                        .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.NONE)).maxEntriesLocalDisk(0)//磁盘中最大缓存对象数0表示无穷大)
                        //.cacheEventListenerFactory(new CacheConfiguration.CacheEventListenerFactoryConfiguration().className(RMICacheReplicatorFactory.class.getName()))
                        )*/
              ;

        return configuration;
    }

}
