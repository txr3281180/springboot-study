package com.txr.spbdatamybatis.service;

import com.github.pagehelper.Page;
import com.txr.spbdatamybatis.controller.condition.BondQueryCondition;
import com.txr.spbdatamybatis.repository.BondInfoProvider;
import com.txr.spbdatamybatis.repository.entity.BondInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/2/16.
 */
@Service
public class BondInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BondInfoProvider bondInfoProvider;

    public Page<Object> getBondsByPage(int page) {
        Page<Object> result = bondInfoProvider.getBondsByPage(page);
        logger.info("query bond {}", result);
        return result;
    }

    public BondInfo getBondByBondKey(String bondKey) {
        return bondInfoProvider.getBondByBondKey(bondKey);
    }

    public List<BondInfo> queryBond(BondQueryCondition bondQueryCondition) {
        return bondInfoProvider.queryBond(bondQueryCondition);
    }

    public List<String> getBondType(){
        return bondInfoProvider.getBondType();
    }
}
