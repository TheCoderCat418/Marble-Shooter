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
    public static Tile[][] map = new Tile[gridSize.x][gridSize.y];
    private static boolean renderStatus = false;
    private static GridPane gp;
    public static void startRendering(GridPane gridpane){
        gp = gridpane;
        boolean a = true;
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
                Pane pane = new Pane();
                pane.setPrefWidth(gp.getPrefWidth() / gridSize.x);
                pane.setPrefHeight(gp.getPrefHeight() / gridSize.y);

                pane.setOnMouseEntered((mouseEvent) -> {
                    Pane l = (Pane) mouseEvent.getSource();
                    System.out.println(l.getStyle());
                    l.setStyle("-fx-background-color: green;");
                });
                pane.setOnMouseExited((mouseEvent) -> {
                    Pane l = (Pane) mouseEvent.getSource();
                    l.setStyle("");
                });
                pane.setOnMouseClicked((mouseEvent) -> {
                    Pane l = (Pane) mouseEvent.getSource();
                    Tile t = findTileFromPane(l);
                    System.out.println(findTileFromPane(pane).tt);
                    System.out.println(t.tt);

                });
                

                

                TileType tt = TileType.EMPTY;
                if(i == 0 || j == 0 || i == gridSize.x-1 || j == gridSize.y-1){
                    map[i][j] = new Boarder(pane);
                }else{
                    map[i][j] = new Tile(pane, tt);
                }

                if(a){
                    map[i][j] = new Entity(pane, null);
                    a = false;
                }






                
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
                Tile t = map[i][j];
                switch (t.tt) {
                    case BOARDER:
                        t.linkedButton.setStyle("-fx-background-color: black;");
    //FIX
                            break;
                    case ENTITY:
                        t.linkedButton.setStyle("-fx-background-color: blue;");
                        t.clockDivider();
                
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

    static public Tile findTileFromPane(Pane pane){
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map[i].length; j++){
                if(map[i][j].linkedButton == pane){
                    return map[i][j];
                }
            }
        }
        return null;
    }

    static public Pane basicPane(){
        Pane pane = new Pane();
                pane.setPrefWidth(gp.getPrefWidth() / gridSize.x);
                pane.setPrefHeight(gp.getPrefHeight() / gridSize.y);

                pane.setOnMouseEntered((mouseEvent) -> {
                    Pane l = (Pane) mouseEvent.getSource();
                    System.out.println(l.getStyle());
                    l.setStyle("-fx-background-color: green;");
                });
                pane.setOnMouseExited((mouseEvent) -> {
                    Pane l = (Pane) mouseEvent.getSource();
                    l.setStyle("");
                });
                pane.setOnMouseClicked((mouseEvent) -> {
                    Pane l = (Pane) mouseEvent.getSource();
                    Tile t = findTileFromPane(l);
                    System.out.println(findTileFromPane(pane).tt);
                    System.out.println(t.tt);

                });
                return pane;
    }
}
