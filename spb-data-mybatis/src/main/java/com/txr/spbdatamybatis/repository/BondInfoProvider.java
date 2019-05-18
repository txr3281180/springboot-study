package com.txr.spbdatamybatis.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.txr.spbdatamybatis.controller.condition.BondQueryCondition;
import com.txr.spbdatamybatis.repository.entity.BondInfo;
import com.txr.spbdatamybatis.repository.mapper.BondInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/2/15.
 */
@Repository
public class BondInfoProvider {

    @Autowired
    private BondInfoMapper bondInfoMapper;

    public Page<Object> getBondsByPage(int page) {
        Page<Object> pageInfo = PageHelper.startPage(page, 20, "id asc");  //添加排序字段
        //pageInfo.setOrderBy("id asc");
        bondInfoMapper.getBondsByPage();
        return pageInfo;
    }

    public BondInfo getBondByBondKey(String bondKey) {
        return bondInfoMapper.getBondByBondKey(bondKey);
    }

    public List<BondInfo> queryBond(BondQueryCondition bondQueryCondition) {
        return bondInfoMapper.getBondByCondition(bondQueryCondition);
    }

    public List<String> getBondType(){
        return bondInfoMapper.getBondType();
    }
}
