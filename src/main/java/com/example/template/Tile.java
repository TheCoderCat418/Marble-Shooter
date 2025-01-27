package com.example.template;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Tile {
    public Pane linkedButton;
    public TileType tt;

    private int clkdvsr = 100;
    private int clkcnt = 0;

    private long a = 0;

    public Tile(Pane linkedButton, TileType tt){
        this.linkedButton = linkedButton;
        linkedButton.setOnMouseClicked((me) -> {
            onClick(me);
        });
        this.tt = tt;
    }

    protected void onClick(MouseEvent me){
        tt  = TileType.ENTITY;
    }

    public void renderRun(){
        System.out.println("I ran. T " + (System.currentTimeMillis()-a));
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
