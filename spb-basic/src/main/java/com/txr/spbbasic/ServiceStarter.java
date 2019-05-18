package com.txr.spbbasic;

import com.txr.spbbasic.global.exception.SpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Spring Boot应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。
 * 也可以利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序。
 */
@Component
@Order(value=1)  //如果有多个启动类,使用Order指定加载顺序
public class ServiceStarter implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * spring.profiles.active=${env_info}  配置
     * 在启动指令后添加参数： java -jar project.jar env_info -env_info=dev
     * spring会自动匹配  application-dev.properties 文件。
     *
     * 也可以直接 java -jar project.jar env_info -spring.profiles.active=dev
     */
    @Value("${server.port}")  //根据不同环境读取不同配置
    private int port;

    @Autowired
    private Environment env;  //读取环境变量

    @Autowired
    private AuthorMapping author;  //配置类型映射

    /**
     * 项目启动时执行
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws SpException {
        logger.info(">>>>>>>>>>>>>>> Spring boot demo 示例 ：author[" + author.getName() + "] <<<<<<<<<<<<<<<");
        logger.info(">>>>>>>>>>>>>>> 根据指定不同配置文件获取对应的端口号 ：" + port + " <<<<<<<<<<<<<<<");
        logger.info("Environment 读取配置信息中的sql : " + env.getProperty("sql.select"));
    }

    //=============================================================================

    /* 启动早于CommandLineRunner.run() */
    @PostConstruct
    public void init() {
        logger.info("Service init todo ======================= ");
    }

    @PreDestroy
    public void destroy() {
        logger.debug("Stop service todo =======================");
    }


    //========================== 线程池配置 ====================================

    @Value("${threadpool.queuecapacity}")
    private int threadPoolQueueCapacity;
    @Value("${threadpool.corepoolsize}")
    private int threadPoolCorePoolSize;
    @Value("${threadpool.maxpoolsize}")
    private int threadPoolMaxPoolSize;
    @Value("${threadpool.keepaliveseconds}")
    private int threadPoolKeepAliveSeconds;

    @Bean
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  //Bean 的作用域
    public TaskExecutor concurrentTaskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setQueueCapacity(threadPoolQueueCapacity);
        poolTaskExecutor.setCorePoolSize(threadPoolCorePoolSize);
        poolTaskExecutor.setMaxPoolSize(threadPoolMaxPoolSize);
        poolTaskExecutor.setKeepAliveSeconds(threadPoolKeepAliveSeconds);
        poolTaskExecutor.initialize();
        return poolTaskExecutor;
    }

}
