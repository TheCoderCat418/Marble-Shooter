package com.thecodercat418.marbleShooter;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Boarder extends Tile {
    public Pane linkedPane;

    public Boarder(Pane linkedPane, Position borderPos) {
        super(borderPos, TileType.BOARDER);
        this.linkedPane = linkedPane;
        linkedPane.setOnMouseClicked((me) -> {
            onClick(me);
        });

    }

    public void onClick(MouseEvent me) {
        if(Render.running){
            return;
        }
        Position p = new Position(0, 0);
        Direction d = null;
        if (tilepos.x == 0) {
            d = Direction.RIGHT;
            p.x++;
        } else if (tilepos.x == Render.gridSize.x - 1) {
            d = Direction.LEFT;
            p.x--;
        } else if (tilepos.y == 0) {
            d = Direction.DOWN;
            p.y++;
        } else if (tilepos.y == Render.gridSize.y - 1) {
            d = Direction.UP;
            p.y--;
        }

        TileType tileType = TileType.EMPTY;

        if (Render.teamSideA) {
            tileType = TileType.B_ENTITY;
            Render.teamSideA = false;
        } else {
            tileType = TileType.A_ENTITY;
            Render.teamSideA = true;
        }

        Render.running = true;

        if (Render.nextTile != TileType.EMPTY) {
            tileType = Render.nextTile;
            Render.nextTile = TileType.EMPTY;

            switch (tileType) {
                case BOMBER:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Bomber(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d, TileType.EMPTY, 1);
                    break;
                    case BIGBOMBER:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Bomber(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d, TileType.EMPTY, 2);
                    break;
                    case HUGEBOMBER:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Bomber(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d, TileType.EMPTY, 3);
                    break;

                case COLOR_BOMBER:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Bomber(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d, TileType.COLOR_BOMBER, 2);
                    break;

                case LTURN:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Turn(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d, true, false);
                    break;

                case RTURN:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Turn(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d, false, false);
                    break;
                    case LRTURN:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Turn(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d, true, true);
                    break;
                    case RLTURN:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Turn(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d, false, true);
                    break;

                case STRIKER:
                    Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Striker(
                            new Position(tilepos.x + p.x, tilepos.y + p.y), d);
                    break;
            }
        } else {
            Render.tileMap[tilepos.x + p.x][tilepos.y + p.y] = new Entity(
                    new Position(tilepos.x + p.x, tilepos.y + p.y), d, tileType, false);
        }

    }

}
