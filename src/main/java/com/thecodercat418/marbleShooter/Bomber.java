package com.thecodercat418.marbleShooter;

public class Bomber extends Entity {
    int radius = 1;
    TileType toMake = TileType.EMPTY;

    public Bomber(Position tilepos, Direction direction, TileType toMake, int radius) {
        super(tilepos, direction, TileType.BOMBER, false);
        this.radius = radius;
        this.toMake = toMake;

        if (toMake.equals(TileType.COLOR_BOMBER)) {
            if (Render.teamSideA) {
                this.toMake = TileType.A_ENTITY;
            } else {
                this.toMake = TileType.B_ENTITY;
            }
        }
    }

    @Override
    public void onFreeze() {
        for (int i = -1; i < radius + 1; i++) {
            for (int j = -1; j < radius + 1; j++) {
                if (super.tilepos.x + i < 0 || super.tilepos.y + j < 0) {
                    continue;
                }
                if (super.tilepos.x + i > Render.gridSize.x - 1 || super.tilepos.y + j > Render.gridSize.y - 1) {
                    continue;
                }
                if (Render.tileMap[super.tilepos.x + i][super.tilepos.y + j].tt.equals(TileType.BOARDER)) {
                    continue;
                }
                Render.tileMap[super.tilepos.x + i][super.tilepos.y + j] = new Tile(
                        new Position(super.tilepos.x + i, super.tilepos.y + j), toMake);

            }
        }
        Render.turnOver();
    }
}
