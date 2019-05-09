package com.txr.spbcacheredis;

import com.alibaba.fastjson.JSON;
import com.spb.redis.spbredis.common.RedisMessage;
import com.spb.redis.spbredis.config.CacheConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by xinrui.tian on 2018/12/14
 */
@Service
public class RedisService {

    private final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private String joinKey(String redisKey) {
        return new StringBuilder(CacheConfig.REDIS_KEY).append(redisKey).toString();
    }

    //String
    private ValueOperations valueOperations() {
        return redisTemplate.opsForValue();
    }

    //Hash
    private HashOperations hashOperations() {
        return redisTemplate.opsForHash();
    }

    //List
    private ListOperations listOperations() {
        return redisTemplate.opsForList();
    }

    //Set
    private SetOperations setOperations() {
        return redisTemplate.opsForSet();
    }

    //ZSet
    private ZSetOperations zSetOperations() {
        return redisTemplate.opsForZSet();
    }

/*
    BoundValueOperations; BoundHashOperations; BoundListOperations; BoundSetOperations; BoundZSetOperations;
    绑定key 的简化操作， 无须显示再次指定key， 提供的方法与下面的差不多

    private BoundValueOperations boundValueOperations() {
        return redisTemplate.boundValueOps(joinKey("name"));
    }
*/

    //===========================================================
    @PostConstruct
    public void testRedis() {
        //testTemplate();  //基本操作
        //testMessage();   //消息
        //testString();    //String
        //testHash();      //Map 操作
        //testList();      //List 操作
        //testSet();       //Set 操作
        //testZSet();      //ZSet 有序集合

        set("test", "1231231");
    }


    private void testTemplate() {
        /** redisTemplate */
        //redisTemplate.watch(REDIS_KEY);
        //redisTemplate.unwatch();
        //SortQuery   redisTemplate.sort()  排序
        //redisTemplate.rename(String oldKey, String newKey);  //key重命名
        //redisTemplate.move(String key, int dbIndex);  //将key移动到index数据库

        Set keys = redisTemplate.keys(joinKey("*"));  //获取匹配的所有key（大数据量可能有性能问题）
        Boolean hasKey = redisTemplate.hasKey(joinKey("name"));  //key 是否存在
        DataType type = redisTemplate.type(joinKey("name"));//确定redis数据类型
        //redisTemplate.countExistingKeys(Collection<K> keys);  //计算keys存在的数量

        //删除Key
        Boolean delete = redisTemplate.delete(joinKey("name"));
        //Long count = redisTemplate.delete(Collection<K> keys)

        //生存时间
        redisTemplate.expire(joinKey("name"), 10000, TimeUnit.SECONDS); //设置生存时间
        //redisTemplate.expireAt(joinKey("name"), new Date());       //将给定key的到期时间设置为日期时间戳。
        Long expire = redisTemplate.getExpire(joinKey("name"));//获取key的生存时间。
        //redisTemplate.getExpire(K key, TimeUnit timeUnit);  //第二个参数换算段位

        //redisTemplate.slaveOfNoOne();   //将服务器更改为master
        //redisTemplate.killClient(String host, int port);   //关闭特定客户端连接
        //redisTemplate.opsForCluster();  //返回特定集群的操作界面
        List<RedisClientInfo> clientList = redisTemplate.getClientList();    //获取已连接客户端的信息
        for (RedisClientInfo redisClientInfo : clientList) {
            String addrEnum = redisClientInfo.get(RedisClientInfo.INFO.ADDRESS_PORT);
            String addr = redisClientInfo.get("addr");
            System.out.println("addrEnum ：" + addrEnum + " addr ：" + addr);

            System.out.println(redisClientInfo.getName());      // //获取客户名称
            System.out.println(redisClientInfo.getAddressPort());  //获取客户端的地址/端口
            System.out.println(redisClientInfo.getAge());       //以秒为单位获取连接的总持续时间
            System.out.println(redisClientInfo.getBufferFreeSpace()); // 获取缓冲区的可用空间
            System.out.println(redisClientInfo.getBufferLength()); //获取缓冲区长度
            System.out.println(redisClientInfo.getChannelSubscribtions());  //获取频道订阅的数量
            System.out.println(redisClientInfo.getDatabaseId());  //获取当前数据库索引
            System.out.println(redisClientInfo.getEvents());  //获取文件描述符事件
            System.out.println(redisClientInfo.getFileDescriptor());  //获取与套接字对应的文件描述符
            System.out.println(redisClientInfo.getFlags());  //获取客户端标志
            System.out.println(redisClientInfo.getIdle());    //在几秒钟内获得连接的空闲时间
            System.out.println(redisClientInfo.getLastCommand());  //获取最后一个命令
            System.out.println(redisClientInfo.getMultiCommandContext());  //获取MULTI / EXEC上下文中的命令数
            System.out.println(redisClientInfo.getOutputBufferMemoryUsage());   //获取输出缓冲内存使用情况
            System.out.println(redisClientInfo.getOutputListLength());  //在输出缓冲区中获取编号排队的回复
            System.out.println(redisClientInfo.getPatternSubscrbtions());  //获取模式订阅的数量
        }
    }

    public void testMessage() {
        //发送消息  param1: channel    param2: [topic, content]
        /*
            // redis 配置 RedisMessage 使用 topic
            RedisMessage meg = new RedisMessage();
            meg.setTopic("topic");
            meg.addContent("megContent", "消息发送");
            redisTemplate.convertAndSend("meg", meg);
        */
        RedisMessage meg = new RedisMessage();
        meg.setTopic("topic");
        meg.addContent("megContent", "消息发送");
        //只能传送 String
        redisTemplate.convertAndSend("meg", JSON.toJSON(meg));
    }

    public void testString() {
        ValueOperations valueOperations = valueOperations();

        //存储
        valueOperations.set(joinKey("testVal"), "java");
        Boolean isSet = valueOperations.setIfAbsent(joinKey("testVal"), "oracle"); //如果key不存在，则设置key-val
        //valueOperations.set(joinKey("testVal"), "java", 10000);
        //valueOperations.set(joinKey("testVal"), "java", 10000, TimeUnit.SECONDS);
        //valueOperations.multiSet(Map var1);  //设置多个key-val，参数为空不覆盖
        //valueOperations.setBit();
        //valueOperations.getBit(K key, long offset);

        //取值
        Long size = valueOperations.size(joinKey("testVal"));   //获取长度
        Object val1 = valueOperations.getAndSet(joinKey("testVal"), "1");//get旧值set新值
        Object val2 = valueOperations.get(joinKey("testVal"));
        //valueOperations.multiGet(Collection<K> keys);  //获取多个

        //运算
        Long increment = valueOperations.increment(joinKey("testVal"), 1); //自增(负数：自减)， 如果存储的是整数字符
        //Double increment1 = valueOperations.increment(joinKey("testVal"), 1.111); //自增， 如果存储的是Double字符
        Integer script = valueOperations.append(joinKey("testVal"), "_12"); //返回拼接字符串的长度

        //RedisOperations operations = valueOperations.getOperations();
    }

    public void testHash() {
        HashOperations hashOperations = hashOperations();

        hashOperations.put(joinKey("bond"), "bondKey", "G0000472015NCD070");
        Boolean aBoolean = hashOperations.putIfAbsent(joinKey("bond"), "numKey", "1");//如果key不存在则put
        //hashOperations.putAll(String key, Map map);       //添加多个hash

        Boolean bondKey1 = hashOperations.hasKey(joinKey("bond"), "bondKey");  //hashKey是否存在
        Long size = hashOperations.size(joinKey("bond"));  //数量

        Object bondKey = hashOperations.get(joinKey("bond"), "bondKey");//
        //hashOperations.multiGet(String key, Collection<HK> hashKeys);     // 获取多个hashKey 的 val
        Set keys = hashOperations.keys(joinKey("bond"));   //获取该key的 所有hashKey, key不存在时返回空集合
        List values = hashOperations.values(joinKey("bond"));   //获取该key hash的所有值
        Map entries = hashOperations.entries(joinKey("bond"));   //获取该key 所有的k-v

        //自增 自减
        Long numKey = hashOperations.increment(joinKey("bond"), "numKey", 1);
        Double numKey1 = hashOperations.increment(joinKey("bond"), "numKey", -1.01);

        //迭代
        //ScanOptions options = ScanOptions.scanOptions().count(1).match("a").build(); //（待研究）
        Cursor bond = hashOperations.scan(joinKey("bond"), ScanOptions.NONE);
        while (bond.hasNext()) {
            Object next = bond.next();
            System.out.println(next.toString());
        }
        hashOperations.delete(joinKey("bond"), "bondKey", "numKey");   //删除多个hashKey
    }

    public void testList() {
        ListOperations listOperations = listOperations();

        //压栈
        Long aLong1 = listOperations.leftPush(joinKey("num"), "1");
        Long index = listOperations.leftPush(joinKey("num"), "1", "before");  //将 value 压入 pivot 之前
        Long aLong2 = listOperations.leftPushAll(joinKey("num"), "2", "3", "4");//左压栈多个
        //listOperations.leftPushAll(joinKey("num"), Collection < V > values);  //左压栈集合
        Long aLong3 = listOperations.leftPushIfPresent(joinKey("num"), "5");//如果列表存在则做压栈

        Long aLong4 = listOperations.rightPush(joinKey("num"), "a");
        Long aLong5 = listOperations.rightPush(joinKey("num"), "1", "after");
        listOperations.rightPushAll(joinKey("num"), "b", "c");
        //listOperations.rightPushAll(K key, Collection < V > values)
        listOperations.rightPushIfPresent(joinKey("num"), "d");

        //操作
        Long size = listOperations.size(joinKey("num"));  //列表长度
        listOperations.set(joinKey("num"), 5, "Z");       //index超出或key不存在是否报错（最好与size一起使用）
        Object index1 = listOperations.index(joinKey("num"), 3);  // 获取坐标index上的元素,下标0开始
        List range = listOperations.range(joinKey("num"), 2, 5);  //获取指定范围的元素  原redis指令特性包含头尾

        //count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
        //count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
        //count = 0 : 移除表中所有与 value 相等的值。
        Long remove = listOperations.remove(joinKey("num"), 2, "4");


        //LTRIM 命令通常和 LPUSH 命令或 RPUSH 命令配合使用  例子：参考文档
        listOperations.trim(joinKey("num"), 0, 5);  //修建列表，范围外的数据全部删除

        //出栈
        Object o1 = listOperations.leftPop(joinKey("num")); //头元素出栈，并返回移除的元素
        Object o2 = listOperations.leftPop(joinKey("num"), 20, TimeUnit.SECONDS); // 阻塞操作（待研究）
        Object o3 = listOperations.rightPop(joinKey("num"));  //尾元素出栈，并返回移除的元素
        //Object o4 = listOperations.rightPop(joinKey("num"), 5, TimeUnit.SECONDS);

        //原子操作
        /*
            将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端.
            将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素.
        */
        //如果 source 和 destination 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作
        Object o5 = listOperations.rightPopAndLeftPush(joinKey("num"), joinKey("num"));
        //Object o6 = listOperations.rightPopAndLeftPush(joinKey("num"), joinKey("num"), 10, TimeUnit.SECONDS);
    }

    public void testSet() {
        SetOperations setOperations = setOperations();  //无序

        //添加
        Long add = setOperations.add(joinKey("keySet1"), "1", "2", "3", "2", "4"); //去重
        Long add2 = setOperations.add(joinKey("keySet2"), "4", "2", "5", "6", "7");

        //操作
        Long keySet21 = setOperations.size(joinKey("keySet1"));
        Boolean member = setOperations.isMember(joinKey("keySet1"), "5");  //判断元素是否为该set成员
        Set keySet1 = setOperations.members(joinKey("keySet1"));
        Object keySet2 = setOperations.randomMember(joinKey("keySet1"));//从set中获取随机元素
        List keySet3 = setOperations.randomMembers(joinKey("keySet1"), 2); //从set中随机获取count个元素（可能出现重复）
        Set keySet31 = setOperations.distinctRandomMembers(joinKey("keySet1"), 2); //从set中随机获取count个不同的元素

        //setOperations.scan(K key, ScanOptions options); //迭代

        /*差集 (返回该set 与其他 set 不同的元素)*/
        Set difference = setOperations.difference(joinKey("keySet2"), joinKey("keySet1"));
        //setOperations.difference(joinKey("keySet2"), Collection < K > otherKeys); //与多个otherKeys比较
        Long aLong = setOperations.differenceAndStore(joinKey("keySet2"), joinKey("keySet1"), joinKey("keySet3"));//找出差集，并保存结果
        //setOperations.differenceAndStore(K key, Collection < K > otherKeys, K destKey); //与多个otherKeys比较， 并保存

        /*并集*/
        Set intersect = setOperations.intersect(joinKey("keySet2"), joinKey("keySet1"));
        //setOperations.intersect(K key, Collection < K > otherKeys);//与多个otherKeys比较
        //setOperations.intersectAndStore(K key, K otherKey, K destKey);    //并集保存
        //setOperations.intersectAndStore(K key, Collection < K > otherKeys, K destKey);

        /*合并*/
        Set union = setOperations.union(joinKey("keySet1"), joinKey("keySet3"));//返回合并结果集
        //setOperations.union(K key, Collection < K > otherKeys); //指定多个otherKeys
        Long aLong1 = setOperations.unionAndStore(joinKey("keySet1"), joinKey("keySet3"), joinKey("keySet4"));//合并将结果保存
        //setOperations.unionAndStore(K key, Collection < K > otherKeys, K destKey); //指定多个otherKeys

        //移除
        Object keySet4 = setOperations.pop(joinKey("keySet1"));//删除并返回一个随机成员
        //List keySet05 = setOperations.pop(joinKey("keySet1"), 2);// 删除并返回count个随机成员
        Boolean move = setOperations.move(joinKey("keySet1"), "8", joinKey("keySet2"));//将元素从key1 移动到 key2（成员不存在不做任何操作）
        Long remove = setOperations.remove(joinKey("keySet1"), "a", "1");  //移除，返回移除的个数(元素不存不做操作)
    }

    public void testZSet() {
        ZSetOperations zSetOperations = zSetOperations();

        //添加 (如果 value已经存在，则只更新score值)
        Boolean add = zSetOperations.add(joinKey("ZSet1"), "java", 1);  //这里的1.0可以用1代替,因为用double收参
        ZSetOperations.TypedTuple<Object> o1 = new DefaultTypedTuple<Object>("oracle", 2.0);//这里必须是2.0，因为那边是用Double收参
        ZSetOperations.TypedTuple<Object> o2 = new DefaultTypedTuple<Object>("redis", 0.0);
        ZSetOperations.TypedTuple<Object> o3 = new DefaultTypedTuple<Object>("nodejs", 2.0); //score相同按照val默认排序
        Set<ZSetOperations.TypedTuple<Object>> set = new HashSet<>();
        set.add(o1);
        set.add(o2);
        set.add(o3);
        Long zSet1 = zSetOperations.add(joinKey("ZSet2"), set);   //批量添加

        //操作
        Long size = zSetOperations.size(joinKey("ZSet2")); //获取size; 底层调用zCard
        Long card = zSetOperations.zCard(joinKey("ZSet2"));//获取size;
        Double score = zSetOperations.score(joinKey("ZSet2"), "nodejs");//获取score分数
        Long rank = zSetOperations.rank(joinKey("ZSet2"), "nodejs");  //获取排名（index）
        Long count = zSetOperations.count(joinKey("ZSet1"), 1, 2);  //返回min与max范围的所有元素 包含头尾

        //为有序集 key 的成员 value 的 score 值加上增量 delta 。 （负数：自减）。
        //当 key 不存在，或 value 不是 key 的成员时，等同于 add(key value delta)
        Double aDouble = zSetOperations.incrementScore(joinKey("ZSet1"), "java", 1.0);

        //迭代
        Cursor<ZSetOperations.TypedTuple<Object>> scan = zSetOperations.scan(joinKey("ZSet1"), ScanOptions.NONE);
        while (scan.hasNext()){
            ZSetOperations.TypedTuple<Object> item = scan.next();
            System.out.println(item.getValue() + ":" + item.getScore());
        }

        /*并集*/
        Long aLong = zSetOperations.intersectAndStore(joinKey("ZSet1"), joinKey("ZSet2"), joinKey("ZSet3"));
        //zSetOperations.intersectAndStore(K key, Collection < K > otherKeys, K destKey);

        /*合并*/
        Long aLong1 = zSetOperations.unionAndStore(joinKey("ZSet1"), joinKey("ZSet2"), joinKey("ZSet4"));
        //zSetOperations.unionAndStore(K key, Collection < K > otherKeys, K destKey);
        //zSetOperations.unionAndStore(K key, Collection < K > otherKeys, K destKey, RedisZSetCommands.Aggregate aggregate);
        //zSetOperations.unionAndStore(K key, Collection < K > otherKeys, K destKey, RedisZSetCommands.Aggregate aggregate, RedisZSetCommands.Weights weights);

        /*范围获取 */
        //获取指定范围内的元素
        Set zSet2 = zSetOperations.range(joinKey("ZSet2"), 1, 5); //下标取值（0开始， 包含头尾）
        //Set zSet3_1 = zSetOperations.rangeByLex(joinKey("ZSet2"), RedisZSetCommands.Range.range().lt("1").gt("5"));// Range参数
        //Set zSet3_2 = zSetOperations.rangeByLex(joinKey("ZSet2"), RedisZSetCommands.Range.range().lte("1").gte("5"), RedisZSetCommands.Limit.limit().offset(2).count(3));  // Limit参数
        Set zSet4 = zSetOperations.rangeByScore(joinKey("ZSet2"), 1, 5);//score取值（包含头尾）
        Set zSet5 = zSetOperations.rangeByScore(joinKey("ZSet2"), 2, 5, 3, 2);
        //通过Score获取指定元素与Score （limit  Range后的元素下标(offset)开始获取count个）
        Set zSet6 = zSetOperations.rangeByScoreWithScores(joinKey("ZSet2"), 1, 5);//score取值（包含头尾）
        Set zSet7 = zSetOperations.rangeByScoreWithScores(joinKey("ZSet2"), 1, 6, 2, -1);
        Set zSet8 = zSetOperations.rangeWithScores(joinKey("ZSet2"), 1, 2); //下标取值（0开始， 包含头尾）

        /*反转后获取元素（高到低 倒序）*/
        //反转后获取元素
        Long aLong2 = zSetOperations.reverseRank(joinKey("ZSet4"), "java"); //反转后获取元素索引（下标）
        Set zSet41 = zSetOperations.reverseRange(joinKey("ZSet4"), 2, 6);//根据下标index (start 0, 包含头尾)
        Set zSet42 = zSetOperations.reverseRangeByScore(joinKey("ZSet4"), 2, 6);//根据 score (包含头尾)
        Set zSet43 = zSetOperations.reverseRangeByScore(joinKey("ZSet4"), 2, 6, 2, 1);//加参数limit
        //反转后获取score与元素
        Set zSet44 = zSetOperations.reverseRangeWithScores(joinKey("ZSet4"), 2, 6);//根据下标index (start 0, 包含头尾)
        Set zSet45 = zSetOperations.reverseRangeByScoreWithScores(joinKey("ZSet4"), 2, 6);////根据 score (包含头尾)
        Set zSet46 = zSetOperations.reverseRangeByScoreWithScores(joinKey("ZSet4"), 2, 6, 2, 1);////加参数limit

        /*移除*/
        Long remove = zSetOperations.remove(joinKey("ZSet4"), "redis", "c++"); //通过元素移除
        Long zSet11 = zSetOperations.removeRange(joinKey("ZSet1"), 0, -1);//通过下标移除 （负数删到结尾）
        Long zSet21 = zSetOperations.removeRangeByScore(joinKey("ZSet2"), 0, 5);//通过score移除 （负数不能删除）
    }

    /**
     * 消息监听
     */
    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(connectionFactory);
        redisMessageListenerContainer.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable throwable) {
                logger.error("redis message listener container error: {}", new Exception(throwable));
            }
        });
        redisMessageListenerContainer.addMessageListener(
                (message, bytes) -> {
                    byte[] body = message.getBody();
                    byte[] channel = message.getChannel();
                    System.out.println(new String(channel) + ":" + new String(body));
                },
                new PatternTopic("meg")  // 发送消息的 channel
        );
        //多个channel 使用多个监听
        /*redisMessageListenerContainer.addMessageListener(
                (message, bytes) -> {
                    byte[] body = message.getBody();
                    byte[] channel = message.getChannel();
                    System.out.println(new String(channel) + ":" + new String(body));
                },
                new PatternTopic("meg1")
        );*/
        return redisMessageListenerContainer;
    }





    //===============================================================================================
    public void set(String redisKey, String content) {
        valueOperations().set(joinKey(redisKey), content);
        logger.info("ValueOperations set to redis: " + redisKey + " " + content);
    }

    public Map<String, Object> getRedis(String redisKey) {
        Object o = valueOperations().get(joinKey(redisKey));

        Map<String, Object> result = new HashMap<>();
        result.put(joinKey(redisKey), o);
        return result;
    }

    public Map<String, Boolean> deleteRedis(String redisKey) {
        Boolean isDelete = redisTemplate.delete(joinKey(redisKey));

        Map<String, Boolean> result = new HashMap<>();
        result.put("deleteCount", isDelete);
        return result;
    }
}
