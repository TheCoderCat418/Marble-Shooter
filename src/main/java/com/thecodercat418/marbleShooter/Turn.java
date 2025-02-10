package com.thecodercat418.marbleShooter;

public class Turn extends Entity {
    boolean isLeft;

    public Turn(Position tilepos, Direction directionStart, boolean isLeft) {
        super(tilepos, directionStart, TileType.LTURN, false);
        this.isLeft = isLeft;
    }

    boolean firstFrozen = true;

    @Override
    public void onFreeze() {
        if (!firstFrozen) {
            TileType toMake;
            if (Render.teamSideA) {
                toMake = TileType.A_ENTITY;
            } else {
                toMake = TileType.B_ENTITY;
            }
            Render.tileMap[tilepos.x][tilepos.y] = new Entity(super.toMoveTo, super.movingDirection, toMake, true);
            Render.turnOver();
            return;
        }
        int base = this.movingDirection.ordinal();
        if (isLeft) {
            base--;
        } else {
            base++;
        }
        if (base > 3) {
            base -= 4;
        } else if (base < 0) {
            base += 4;
        }
        super.movingDirection = Direction.values()[base];
        super.setToMoveTo(Direction.values()[base]);

        super.frozen = false;
        firstFrozen = false;
    }

}
