package com.txr.spbbasic.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by xinrui.tian on 2018/12/14
 */
@ApiModel("响应数据")
public class ResponseData<T> implements Serializable {

    @ApiModelProperty(value = "响应状态", required = true, position = 1)
    private MetaData meta;
    @ApiModelProperty(value = "响应结果", position = 2)
    private T data;

    public ResponseData() {
    }

    public ResponseData(MetaData meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public MetaData getMeta() {
        return this.meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "ResponseData {meta=" + this.meta + ", data=" + this.data + "}";
    }
}