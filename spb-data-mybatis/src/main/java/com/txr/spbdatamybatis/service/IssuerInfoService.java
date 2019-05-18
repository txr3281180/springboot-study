package com.txr.spbdatamybatis.service;


import com.txr.spbdatamybatis.repository.IssuerInfoProvider;
import com.txr.spbdatamybatis.repository.entity.IssuerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xinrui.tian on 2019/2/16.
 */
@Service
public class IssuerInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IssuerInfoProvider issuerInfoProvider;

    public IssuerInfo getIssuerByIssuerCode(String issuerCode) {
        return issuerInfoProvider.getIssuerByIssuerCode(issuerCode);
    }

}
