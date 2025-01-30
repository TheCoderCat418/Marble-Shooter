package com.example.template;


public class Tile {
    public Position tilepos;
    public TileType tt;
    public boolean frozen = false;

    private int clkdvsr = 100;
    private int clkcnt = 0;

    // private long a = 0;

    public Tile(Position tilepos, TileType tt){
        this.tilepos = tilepos;
        
        this.tt = tt;
    }

    public void renderRun(){
        // System.out.println("I ran. T " + (System.currentTimeMillis()-a));
        // a = System.currentTimeMillis();
    }

    public void clockDivider(){
        if(clkdvsr <= 1){
            renderRun();
            return;
        }

        if(clkdvsr > clkcnt){
            clkcnt++;
        }else{
            renderRun();
            clkcnt = 0;
            return;
        }
    }

    

    
}
