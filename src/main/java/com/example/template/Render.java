package com.example.template;


import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class Render {
    private static Position gridSize = new Position(10, 10);
    private static Tile[][] map = new Tile[gridSize.x][gridSize.y];
    private static boolean renderStatus = false;
    public static void startRendering(GridPane gp){
        if(renderStatus){
            return;
        }
        gp.getChildren().clear();
        for(int i = 0; i < gridSize.x; i++){
            gp.getColumnConstraints()
                    .add(new ColumnConstraints(gp.getPrefWidth() / gridSize.x));
            for(int j = 0; j < gridSize.y; j++){
                gp.getRowConstraints()
                        .add(new RowConstraints(gp.getPrefHeight() / gridSize.y));
                Pane aLabel = new Pane();
                aLabel.setPrefWidth(gp.getPrefWidth() / gridSize.x);
                aLabel.setPrefHeight(gp.getPrefHeight() / gridSize.y);

                aLabel.setOnMouseEntered((mouseEvent) -> {
                    Pane l = (Pane) mouseEvent.getSource();
                    System.out.println(l.getStyle());
                    l.setStyle("-fx-background-color: green;");
                });
                aLabel.setOnMouseExited((mouseEvent) -> {
                    Pane l = (Pane) mouseEvent.getSource();
                    l.setStyle("");
                });

                TileType tt = TileType.EMPTY;
                if(i == 0 || j == 0 || i == gridSize.x-1 || j == gridSize.y-1){
                    tt = TileType.BOARDER;
                }

                






                map[i][j] = new Tile(aLabel,tt);
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
                        map[i][j].linkedButton.setStyle("-fx-background-color: black");
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
