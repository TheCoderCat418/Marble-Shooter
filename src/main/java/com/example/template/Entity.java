package com.example.template;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Entity extends Tile{
    private Position toMoveTo;
    private Position cPosition;

    private int clkdvsr = 50;
    private int clkcnt = 0;

    private long a = 0;


    public Entity(Pane linkedButton, Position cPosition){
        super(linkedButton, TileType.ENTITY);
        this.cPosition = cPosition;
    }
    
    public void renderRun(){ //TASKS: MOVE
        System.out.println("I ran. E " + (System.currentTimeMillis()-a));
        a = System.currentTimeMillis();
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
