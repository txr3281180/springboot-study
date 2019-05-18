package com.txr.spbbasic.model.builder;

public class TestBuilder {

    public static void main(String[] args) {
        HouseBuilder h = new PingFangHouse();

        HouseDirector hb = new HouseDirector();

        hb.makeHouse(h);

        House house = h.getHouse();
        System.out.println(house);


        System.out.println(house.getFloor());
        System.out.println(house.getWall());
        System.out.println(house.getHouseTop());

    }
}
