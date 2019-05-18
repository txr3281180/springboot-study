package com.txr.spbbasic.model.facade;

public class MainClass {

    public static void main(String[] args) {
        GamePlatform gp = new GamePlatform();

        gp.playGame();
        System.out.println("-----------------------");
        gp.playGameAB();

    }
}
