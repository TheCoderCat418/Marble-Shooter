package com.thecodercat418.marbleShooter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HelloController {
  @FXML
  private GridPane playBoard;
  public static TileType nextTile = TileType.EMPTY;

  public void initialize() {
    Render.startRendering(playBoard);
  }

  public void nextPiece(ActionEvent actionEvent) {
    Button b = (Button) actionEvent.getSource();
    switch (b.getText()) {
      case "Bomb":
        nextTile = TileType.BOMBER;
        break;
      case "Left Turn":
        nextTile = TileType.LTURN;
        break;
      case "Right Turn":
        nextTile = TileType.RTURN;
        break;
      case "Striker":
        nextTile = TileType.STRIKER;
        break;
    }
  }

}
