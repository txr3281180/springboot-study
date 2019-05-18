package com.txr.spbbasic.controller.response;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xinrui.tian on 2018/12/14
 */
public class ResponseUtil {

    public static <T> ResponseData<T> ok(T data) {
        return new ResponseData(new MetaData(ErrorNum.SUCCESS.value(), "success", (String)null, (String)null), data);
    }

    public static ResponseData<VoidData> error(ErrorNum errNum, String errMsg, HttpServletRequest request) {
        return new ResponseData(new MetaData(errNum.value(), errMsg, request.getMethod(), request.getRequestURI()), VoidData.VOID);
    }
}
