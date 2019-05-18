package com.txr.spbbasic.global.exception;


import com.txr.spbbasic.controller.response.ErrorNum;

public abstract class SpException extends Exception {

    private static final long serialVersionUID = 1L;

    private ErrorNum errorNum = ErrorNum.SUCCESS;

    public ErrorNum getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(ErrorNum errorNum) {
        this.errorNum = errorNum;
    }

    public SpException() {
        super();
    }

    public SpException(String message) {
        super(message);
    }

    public SpException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpException(Throwable cause) {
        super(cause);
    }

    public SpException(ErrorNum errorNum, String message) {
        super(message);
        this.errorNum = errorNum;
    }

    public SpException(ErrorNum errorNum, String message, Throwable cause) {
        super(message, cause);
        this.errorNum = errorNum;
    }
}
