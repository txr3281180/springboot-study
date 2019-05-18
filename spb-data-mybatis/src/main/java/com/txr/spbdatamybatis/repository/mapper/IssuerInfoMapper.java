package com.txr.spbdatamybatis.repository.mapper;


import com.txr.spbdatamybatis.repository.entity.IssuerInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by xinrui.tian on 2019/4/6.
 *
 * xml 配置 示例
 *
 */
@Repository
public interface IssuerInfoMapper {

    IssuerInfo getIssuerByIssuerCode(String issuerCode);
}
