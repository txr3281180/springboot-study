package com.txr.spbdatamybatis.repository;


import com.txr.spbdatamybatis.repository.entity.IssuerInfo;
import com.txr.spbdatamybatis.repository.mapper.IssuerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by xinrui.tian on 2019/4/6.
 */
@Repository
public class IssuerInfoProvider {

    @Autowired
    private IssuerInfoMapper issuerInfoMapper;

    public IssuerInfo getIssuerByIssuerCode(String issuerCode) {
        return issuerInfoMapper.getIssuerByIssuerCode(issuerCode);
    }
}
