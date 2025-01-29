package com.example.template;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Boarder extends Tile{
    public Pane linkedPane;
    public Boarder(Pane linkedPane, Position borderPos){
        super(borderPos, TileType.BOARDER);
        this.linkedPane = linkedPane;
        linkedPane.setOnMouseClicked((me) -> {
            onClick(me);
        });
        
    }

    protected void onClick(MouseEvent me){
        // Position p = Render.findPositionFromTile(this);
        // System.out.println(p.toString());
    }

    
}
