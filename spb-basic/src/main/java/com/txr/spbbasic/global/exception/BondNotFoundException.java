package com.txr.spbbasic.global.exception;

import com.txr.spbbasic.controller.response.ErrorNum;

/**
 * Created by xinrui.tian on 2019/1/2
 */
public class BondNotFoundException extends SpException {

    public BondNotFoundException(String bondKey) {
        super(ErrorNum.WB_BOND_NOT_FOUND, String.format("The bondKey [%s] not exists.", bondKey));
    }
}
