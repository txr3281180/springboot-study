package com.txr.spbbasic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xinrui.tian on 2018/11/13.
 */
@Api(tags = "AdminAPI")
@RestController
@RequestMapping(value = "/txr/admin", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AdminController {

    @ApiOperation(value = "获取swagger常用注解", notes = "获取swagger常用注解")
    @GetMapping(value = "/swagger/annotation")
    public String getSwaggerAnnotation() {
        return "{\"@Api\":\"用于类\", \"@ApiOperation\":\"用于方法\"," +
                "\"@ApiParam\":\"用于方法、方法参数、字段\",\"@ApiModel\":\"用于类\"," +
                "\"@ApiModelProperty\":\"用于方法和字段\",\"@ApiIgnore\":\"用于类、方法、方法参数\"," +
                "\"@ApiImplicitParam\":\"用于方法\",\"@ApiImplicitParams\":\"用于方法\"}";
    }

}
