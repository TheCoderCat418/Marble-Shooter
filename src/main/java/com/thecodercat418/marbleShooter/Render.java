package com.thecodercat418.marbleShooter;

import javafx.animation.AnimationTimer;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class Render {
    public static Position gridSize = new Position(20, 20);
    public static Tile[][] tileMap = new Tile[gridSize.x][gridSize.y];
    public static Pane[][] paneMap = new Pane[gridSize.x][gridSize.y];
    // TODO: Tri-Layer? Animation Map??
    private static boolean renderStatus = false;
    public static boolean teamSideA = true;
    private static GridPane gp;

    public static void startRendering(GridPane gridpane) {
        gp = gridpane;
        if (renderStatus) {
            return;
        }
        gp.getChildren().clear();

        for (int i = 0; i < gridSize.x; i++) {
            gp.getColumnConstraints()
                    .add(new ColumnConstraints(gp.getPrefWidth() / gridSize.x));
            for (int j = 0; j < gridSize.y; j++) {
                gp.getRowConstraints()
                        .add(new RowConstraints(gp.getPrefHeight() / gridSize.y));
                Pane pane = new Pane();
                pane.setPrefWidth(gp.getPrefWidth() / gridSize.x);
                pane.setPrefHeight(gp.getPrefHeight() / gridSize.y);

                pane.setOnMouseEntered((mouseEvent) -> {
                    // Pane l = (Pane) mouseEvent.getSource();
                    // System.out.println(l.getStyle());
                    // l.setStyle("-fx-background-color: green;");

                    // Perfect place to use a render map
                });
                pane.setOnMouseExited((mouseEvent) -> {
                    // Pane l = (Pane) mouseEvent.getSource();
                    // l.setStyle("");
                });

                // MOUSE CLICK VERY TRICKY
                // Would need a proxy class (Probobly this one)
                // pane.setOnMouseClicked((mouseEvent) -> {
                // Pane l = (Pane) mouseEvent.getSource();
                // Tile t = findTileFromPane(l);
                // System.out.println(findTileFromPane(pane).tt);
                // System.out.println(t.tt);

                // });

                TileType tt = TileType.EMPTY;
                if (i == 0 || j == 0 || i == gridSize.x - 1 || j == gridSize.y - 1) {
                    tileMap[i][j] = new Boarder(pane, new Position(i, j));
                } else {
                    tileMap[i][j] = new Tile(new Position(i, j), tt);
                }
                paneMap[i][j] = pane;
                gp.add(paneMap[i][j], i, j);
            }
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                renderLoop();
            }
        }.start();
        renderStatus = true;
    }

    static private void renderLoop() {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                Tile t = tileMap[i][j];
                if (t.frozen) {
                    continue;
                }// Still update colors
                Pane pane = findPaneFromTile(t);

                switch (t.tt) {
                    case BOARDER:
                        pane.setStyle("-fx-background-color: black;");
                        break;
                    case A_ENTITY:
                        pane.setStyle("-fx-background-color: red;");
                        t.clockDivider();
                        break;
                    case B_ENTITY:
                        pane.setStyle("-fx-background-color: blue;");
                        t.clockDivider();
                        break;
                    case BOMBER:
                        pane.setStyle("-fx-background-color: brown;");
                        t.clockDivider();
                        break;
                    case LTURN:
                        pane.setStyle("-fx-background-color: green;");
                        t.clockDivider();
                        break;
                    case RTURN:
                        pane.setStyle("-fx-background-color: green;");
                        t.clockDivider();
                        break;
                    case EMPTY:
                        pane.setStyle("");
                        break;

                }

            }
        }
    }

    static public Pane findPaneFromTile(Tile tile) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (tileMap[i][j] == tile) {
                    return paneMap[i][j];
                }
            }
        }
        return null;
    }

    // static public Tile findTileFromPane(Pane pane){
    // for(int i = 0; i<map.length; i++){
    // for(int j = 0; j<map[i].length; j++){
    // if(map[i][j].linkedButton == pane){
    // return map[i][j];
    // }
    // }
    // }
    // return null;
    // }

    // static public Pane basicPane(){
    // Pane pane = new Pane();
    // pane.setPrefWidth(gp.getPrefWidth() / gridSize.x);
    // pane.setPrefHeight(gp.getPrefHeight() / gridSize.y);

    // pane.setOnMouseEntered((mouseEvent) -> {
    // Pane l = (Pane) mouseEvent.getSource();
    // System.out.println(l.getStyle());
    // l.setStyle("-fx-background-color: green;");
    // });
    // pane.setOnMouseExited((mouseEvent) -> {
    // Pane l = (Pane) mouseEvent.getSource();
    // l.setStyle("");
    // });
    // pane.setOnMouseClicked((mouseEvent) -> {
    // Pane l = (Pane) mouseEvent.getSource();
    // Tile t = findTileFromPane(l);
    // System.out.println(findTileFromPane(pane).tt);
    // System.out.println(t.tt);

    // });
    // return pane;
    // }
}
