package com.txr.spbbasic.controller.response;

/**
 * Created by xinrui.tian on 2018/12/14
 */
public final class ErrorNum {
    public static final ErrorNum SUCCESS = new ErrorNum(0, "无错误");
    public static final ErrorNum INVALID_ARGUMENT = new ErrorNum(400, "参数错误");
    public static final ErrorNum WB_EVENT_NOT_FOUND = new ErrorNum(170001, "债券事件不存在");
    public static final ErrorNum WB_BOND_NOT_FOUND = new ErrorNum(180001, "债券不存在");
    public static final ErrorNum WB_LISTEDBOND_NOT_FOUND = new ErrorNum(180002, "流通债券不存在");
    public static final ErrorNum WB_ISSUER_NOT_FOUND = new ErrorNum(190001, "发行人不存在");
    public static final ErrorNum WB_USER_NO_DATAACCESS = new ErrorNum(480001, "用户没权限访问数据");
    public static final ErrorNum SERVICE_ERROR = new ErrorNum(500000, "未知服务错误");
    public static final ErrorNum NOT_IMPLEMENTED = new ErrorNum(599999, "API未实现");
    private int errNum;
    private String description;

    public ErrorNum(int errNum, String description) {
        this.errNum = errNum;
        this.description = description;
    }

    public int value() {
        return this.errNum;
    }

    public String description() {
        return this.description;
    }
}
