package com.txr.spbbasic.model.builder;

public class HouseDirector {
    //开始修建
    public void makeHouse(HouseBuilder hb){
        hb.buildFloor();;
        hb.buildHouseTop();
        hb.buildWall();
    }

}
