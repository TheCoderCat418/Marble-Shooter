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
        //Render.tileMap[1][1] = new Entity(new Position(1, 1), Direction.RIGHT);
        Position p = new Position(0, 0);
        Direction d = null;
        if(tilepos.x == 0){
            d = Direction.RIGHT;
            p.x++;
        } else if(tilepos.x == Render.gridSize.x-1){
            d = Direction.LEFT;
            p.x--;
        } else if(tilepos.y == 0){
            d = Direction.DOWN;
            p.y++;
        } else if(tilepos.y == Render.gridSize.y-1){
            d = Direction.UP;
            p.y--;
        }

        if(Render.teamSideA){
            Render.tileMap[tilepos.x+p.x][tilepos.y+p.y] = new Entity(new Position(tilepos.x+p.x, tilepos.y+p.y), d, TileType.A_ENTITY);
            Render.teamSideA = false;
        } else {
            Render.tileMap[tilepos.x+p.x][tilepos.y+p.y] = new Entity(new Position(tilepos.x+p.x, tilepos.y+p.y), d, TileType.B_ENTITY);
            Render.teamSideA = true;
        }

        
    }

    
}
