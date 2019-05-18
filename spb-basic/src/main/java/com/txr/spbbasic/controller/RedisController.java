package com.txr.spbbasic.controller;



import com.txr.spbbasic.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xinrui.tian on 2018/12/14
 */
@Api(tags = "RedisAPI")
@RestController
@RequestMapping(path = "txr/study/redis", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "添加 redis 数据")
    @PutMapping(value = "/")
    public void pushRedis (@ApiParam(value = "redisKey", required = true)
                           @RequestParam String redisKey,
                           @ApiParam(value = "redisValue", required = true)
                           @RequestParam String content) {
        redisService.set(redisKey, content);
    }

    @ApiOperation(value = "获取 redis 数据")
    @GetMapping(value = "/")
    public Map<String, Object> getRedis (@ApiParam(value = "redisKey", required = true)
                            @RequestParam String redisKey) {

        return redisService.getRedis(redisKey);
    }

    @ApiOperation(value = "删除 redis 数据")
    @DeleteMapping(value = "/")
    public Map<String, Boolean> delRedis (@ApiParam(value = "redisKey", required = true)
                          @RequestParam String redisKey) {
        return redisService.deleteRedis(redisKey);
    }

    @ApiOperation(value = "redis 测试方法")
    @GetMapping(value = "/test")
    public void testRedis () {
        redisService.testRedis();
    }
}
