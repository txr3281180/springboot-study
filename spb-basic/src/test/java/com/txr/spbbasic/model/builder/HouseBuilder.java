package com.txr.spbbasic.model.builder;

//生产蓝图
public interface HouseBuilder {
    void buildFloor();
    void buildWall();
    void buildHouseTop();
    House getHouse();
}
