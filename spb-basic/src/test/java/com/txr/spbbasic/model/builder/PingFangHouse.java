package com.txr.spbbasic.model.builder;

public class PingFangHouse implements HouseBuilder {
    House h = new House();

    @Override
    public void buildFloor() {
        h.setFloor("平房 --> 地板");
    }

    @Override
    public void buildWall() {
        h.setWall("平房 --> 墙");
    }

    @Override
    public void buildHouseTop() {
        h.setHouseTop("平房 --> 屋顶");
    }

    @Override
    public House getHouse() {
        return h;
    }
}
