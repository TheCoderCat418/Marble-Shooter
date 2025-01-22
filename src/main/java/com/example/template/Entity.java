package com.example.template;

import javafx.scene.control.Button;

public class Entity extends Tile{
    private Position toMoveTo;


    public Entity(Button linkedButton){
        super(linkedButton, TileType.ENTITY);
    }

    
    public void renderRun(){ //TASKS: MOVE

    }

    
}
