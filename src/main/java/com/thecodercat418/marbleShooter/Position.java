package com.thecodercat418.marbleShooter;

public class Position {
    int x;
    int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "X: " + x + " Y: " + y;
    }
}
