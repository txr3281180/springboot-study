package com.txr.spbbasic.model.facade;

//游戏平台
public class GamePlatform {

    private GameA a;
    private GameB b;
    private GameC c;

    public GamePlatform(){
        a = new GameA();
        b = new GameB();
        c = new GameC();
    }

    public void playGame(){
        a.playGame();
        b.playGame();
        c.playGame();
    }

    public void playGameAB(){
        a.playGame();
        b.playGame();
    }
}
