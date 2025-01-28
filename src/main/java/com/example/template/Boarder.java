package com.example.template;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Boarder extends Tile{
    public Boarder(Pane linkedButton){
        super(linkedButton, TileType.BOARDER);
    }

    @Override
    protected void onClick(MouseEvent me){
        Position p = Render.findPositionFromTile(this);
        System.out.println(p.toString());
    }

    
}
