package com.txr.spbbasic.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by xinrui.tian on 2018/12/14
 */
@ApiModel("响应状态数据")
public class MetaData implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            value = "错误码",
            required = true,
            position = 1
    )
    private int errNum;
    @ApiModelProperty(
            value = "错误消息",
            position = 2
    )
    private String errMsg;
    @ApiModelProperty(
            value = "请求方法",
            position = 3
    )
    private String requestMethod;
    @ApiModelProperty(
            value = "请求URI",
            position = 4
    )
    private String requestURI;

    public MetaData() {
    }

    public MetaData(int errNum, String errMsg, String method, String uri) {
        this.errNum = errNum;
        this.errMsg = errMsg;
        this.requestMethod = method;
        this.requestURI = uri;
    }

    public int getErrNum() {
        return this.errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestURI() {
        return this.requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String toString() {
        return "MetaData {errNum=" + this.errNum + ", errMsg=" + this.errMsg + ", requestMethod=" + this.requestMethod + ", requestURI=" + this.requestURI + "}";
    }
}

