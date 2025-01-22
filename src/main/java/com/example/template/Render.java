package com.example.template;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Render {
    private static Position gridSize;
    private static Tile[][] map = new Tile[gridSize.x/10][gridSize.y/10];
    private static boolean renderStatus = false;
    public static void startRendering(GridPane gp){
        if(renderStatus){
            return;
        }
        for(int i = 0; i < gridSize.x/10; i++){
            for(int j = 0; j < gridSize.y/10; j++){
                map[i][j] = new Tile(new Button("a"),TileType.BOARDER);
                gp.add(map[i][j].linkedButton, i, j);
            }
        }
        
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                renderLoop();
            }
        }.start();
        renderStatus = true;
    }

    static private void renderLoop(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j].tt) {
                    case BOARDER:
                        
                        break;
                
                    default:
                        break;
                }
            }
        }
    }




    static public Position findPositionFromTile(Tile tile){
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map[i].length; j++){
                if(map[i][j] == tile){
                    return new Position(i, j);
                }
            }
        }
        return null;
    }
}
