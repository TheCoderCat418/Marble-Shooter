package com.example.template;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Entity extends Tile{
    private Position toMoveTo = new Position(7, 7);
    private Position cPosition = new Position(0, 0);

    private int clkdvsr = 50;
    private int clkcnt = 0;

    private long a = 0;


    public Entity(Pane linkedButton, Position cPosition){
        super(linkedButton, TileType.ENTITY);
        //this.cPosition = cPosition;
    }
    
    public void renderRun(){ //TASKS: MOVE
        System.out.println("I ran. E " + (System.currentTimeMillis()-a));
        a = System.currentTimeMillis();

        if(cPosition.x < toMoveTo.x){
            cPosition.x++;
        }else if(cPosition.x > toMoveTo.x){
            cPosition.x--;
        }
        if(cPosition.y < toMoveTo.y){
            cPosition.y++;
        }else if(cPosition.y > toMoveTo.y){
            cPosition.y--;
        }
        Position p = Render.findPositionFromTile(Render.findTileFromPane(linkedButton));
        Render.map[cPosition.x][cPosition.y].tt = Render.map[p.x][p.y].tt;
        Render.map[cPosition.x][cPosition.y].linkedButton = Render.map[p.x][p.y].linkedButton;
        Render.map[p.x][p.y].linkedButton = Render.basicPane();
        Render.map[p.x][p.y].tt = TileType.EMPTY;
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
