package com.thecodercat418.marbleShooter;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class Render {
  @FXML
  private GridPane playBoard;
  @FXML
  private PieChart pie;
  public static PieChart publicpie;
  public static TileType nextTile = TileType.EMPTY;
  public static boolean running = false;
  public int p1Credits = 1000;
  public int p2Credits = 1000;
  public Label ta1;
  public Label ta2;
  public static Label staticta1;
  public static Label staticta2;
  public Label p1CreditsLabel;
  public Label p2CreditsLabel;
  public Label gmor;


  public void initialize() {
    startRendering(playBoard);
    publicpie = pie;
    staticta1 = ta1;
    staticta2 = ta2;
  }

  public void nextPiece(ActionEvent actionEvent) {
    if(running){
        return;
    }
    Button b = (Button) actionEvent.getSource();
    int toSub = -5;
    switch (b.getText()) {
      case "Bomb":
        nextTile = TileType.BOMBER;
        toSub = -20;
        break;
      case "Left Turn":
        nextTile = TileType.LTURN;
        toSub = -15;
        break;
      case "Right Turn":
        nextTile = TileType.RTURN;
        toSub = -15;
        break;
      case "Striker":
        nextTile = TileType.STRIKER;
        toSub = -30;
        break;
      case "Color Bomber":
        nextTile = TileType.COLOR_BOMBER;
        toSub = -25;
        break;
        case "Bigger Bomb":
        nextTile = TileType.BIGBOMBER;
        toSub = -30;
        break;
        case "Biggest Bomb":
        nextTile = TileType.HUGEBOMBER;
        toSub = -50;
        break;
        case "Right then Left":
        nextTile = TileType.RLTURN;
        toSub = -30;
        break;
        case "Left then Right":
        nextTile = TileType.LRTURN;
        toSub = -30;
        break;
      default:
        nextTile = TileType.EMPTY;
        toSub = -10;
        break;
    }
    if(teamSideA){
        p1Credits+=toSub;
    }else{
        p2Credits+=toSub;
    }
    
    p1CreditsLabel.setText("Player One: " + p1Credits);
    p2CreditsLabel.setText("Player Two: " + p2Credits);
    //Make all buttons lock until done

  }
  public static void turnOver(){
    int a =0;
    int b = 0;
    int e = 0;
    for (int i = 0; i < tileMap.length; i++) {
        for (int j = 0; j < tileMap[0].length; j++) {
            Tile t = tileMap[i][j];
            switch (t.tt) {
                case A_ENTITY:
                    a++;
                    break;
                case B_ENTITY:
                    b++;
                    break;
                case EMPTY:
                e++;
                break;

            }

        }
    }
    publicpie.getData().set(0, new Data("Empty", e));
    publicpie.getData().set(1, new Data("Red", a));
    publicpie.getData().set(2, new Data("Blue", b));
    staticta1.setText("Tiles Occupied: " + a);
    staticta2.setText("Tiles Occupied: " + b);
    running = false;

  }


   public static Position gridSize = new Position(20, 20);
    public static Tile[][] tileMap = new Tile[gridSize.x][gridSize.y];
    public static Pane[][] paneMap = new Pane[gridSize.x][gridSize.y];
    // TODO: Tri-Layer? Animation Map??
    private static boolean renderStatus = false;
    public static boolean teamSideA = true;
    private static GridPane gp;

    private void startRendering(GridPane gridpane) {
        gmor.setVisible(false);
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
            pie.getData().clear();
            pie.getData().add(new Data("Blank Spaces", (gridSize.x-2)*(gridSize.y-2)));
            pie.getData().add(new Data("Blank Spaces", (gridSize.x-2)*(gridSize.y-2)));
            pie.getData().add(new Data("Blank Spaces", (gridSize.x-2)*(gridSize.y-2)));
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                renderLoop();

            }
        }.start();
        renderStatus = true;
    }

    private void renderLoop() {
        if(p1Credits<0 || p2Credits<0){
            running = true;
            gmor.setVisible(true);
        }
        int a = 0;
        int b = 0;
        
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                Tile t = tileMap[i][j];
                if (t.frozen) {
                    continue;
                } // Still update colors
                Pane pane = findPaneFromTile(t);

                switch (t.tt) {
                    case BOARDER:
                        pane.setStyle("-fx-background-color: black;");
                        break;
                    case A_ENTITY:
                        pane.setStyle("-fx-background-color: red;");
                        a++;
                        t.clockDivider();
                        break;
                    case B_ENTITY:
                        pane.setStyle("-fx-background-color: blue;");
                        b++;
                        t.clockDivider();
                        break;
                    case COLOR_BOMBER:
                    case BIGBOMBER:
                    case HUGEBOMBER:
                    case BOMBER:
                        pane.setStyle("-fx-background-color: brown;");
                        t.clockDivider();
                        break;
                    case LTURN:
                    case LRTURN:
                    case RLTURN:
                    case RTURN:
                        pane.setStyle("-fx-background-color: green;");
                        t.clockDivider();
                        break;
                    case STRIKER:
                        pane.setStyle("-fx-background-color: orange;");
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

  
}
