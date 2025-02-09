package com.thecodercat418.marbleShooter;

public class Striker extends Entity {
    Direction d;
    TileType toMake;

    public Striker(Position tilepos, Direction direction) {
        super(tilepos, direction, TileType.STRIKER);
        int base = this.movingDirection.ordinal();
        base += 2;
        if (base > 3) {
            base -= 4;
        } else if (base < 0) {
            base += 4;
        }
        d = Direction.values()[base];

        if (Render.teamSideA) {
            this.toMake = TileType.A_ENTITY;
        } else {
            this.toMake = TileType.B_ENTITY;
        }

    }

    @Override
    public void move() {
        Position oldPos = new Position(tilepos.x, tilepos.y);
        if (tilepos.x != toMoveTo.x || tilepos.y != toMoveTo.y) {
            if (tilepos.x < toMoveTo.x) {
                tilepos.x++;
            } else if (tilepos.x > toMoveTo.x) {
                tilepos.x--;
            }
            if (tilepos.y < toMoveTo.y) {
                tilepos.y++;
            } else if (tilepos.y > toMoveTo.y) {
                tilepos.y--;
            }
            System.out.println(oldPos);
            System.out.println(tilepos);
            Render.tileMap[tilepos.x][tilepos.y] = Render.tileMap[oldPos.x][oldPos.y];
            Render.tileMap[oldPos.x][oldPos.y] = new Entity(oldPos, d, toMake);
            return;
        }
        Render.tileMap[tilepos.x][tilepos.y] = new Entity(oldPos, d, toMake);
        frozen = true;
        onFreeze();
    }
}
