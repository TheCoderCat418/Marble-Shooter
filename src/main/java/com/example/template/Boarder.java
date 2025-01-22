package com.example.template;

import javafx.scene.control.Button;

public class Boarder extends Tile{
    public Boarder(Button linkedButton){
        super(linkedButton, TileType.BOARDER);
    }
}
