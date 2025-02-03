package com.thecodercat418.marbleShooter;

public class Bomber extends Entity {

    public Bomber(Position tilepos, Direction direction) {
        super(tilepos, direction, TileType.BOMBER);

    }

    @Override
    public void onFreeze() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                //if(super.tilepos.x+i < -1)
                Render.tileMap[super.tilepos.x+i][super.tilepos.y+j] = new Tile(new Position(super.tilepos.x+i, super.tilepos.y+j), TileType.EMPTY);

            }
        }
    }
}
