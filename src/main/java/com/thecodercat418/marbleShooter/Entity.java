package com.thecodercat418.marbleShooter;

public class Entity extends Tile {
    public Position toMoveTo;
    public Abilities currentAbility;
    public Direction movingDirection;
    public long a = 0;

    public Entity(Position tilepos, Direction direction, TileType t) {
        super(tilepos, t);
        super.clkdvsr = 10;
        System.out.println(tilepos);
        this.movingDirection = direction;
        this.tilepos = tilepos;
        setToMoveTo(direction);
    }

    public void setToMoveTo(Direction direction) {
        Position indexPos = new Position(tilepos.x, tilepos.y);
        boolean finalized = false;
        while (!finalized) {
            switch (direction) {
                case UP:
                    indexPos.y--;
                    if (!Render.tileMap[indexPos.x][indexPos.y].tt.equals(TileType.EMPTY)) {
                        indexPos.y++;
                        toMoveTo = indexPos;
                        finalized = true;
                    }
                    break;
                case DOWN:
                    indexPos.y++;
                    if (!Render.tileMap[indexPos.x][indexPos.y].tt.equals(TileType.EMPTY)) {
                        indexPos.y--;
                        toMoveTo = indexPos;
                        finalized = true;
                    }
                    break;
                case LEFT:
                    indexPos.x--;
                    if (!Render.tileMap[indexPos.x][indexPos.y].tt.equals(TileType.EMPTY)) {
                        indexPos.x++;
                        toMoveTo = indexPos;
                        finalized = true;
                    }
                    break;
                case RIGHT:
                    indexPos.x++;
                    if (!Render.tileMap[indexPos.x][indexPos.y].tt.equals(TileType.EMPTY)) {
                        indexPos.x--;
                        toMoveTo = indexPos;
                        finalized = true;
                    }
                    break;
                default:
                    break;
            }
        }
        currentAbility = Abilities.MOVE;
    }

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
            Render.tileMap[oldPos.x][oldPos.y] = new Tile(oldPos, TileType.EMPTY);

            return;
        }
        frozen = true;
        onFreeze();
    }

    public void onFreeze() {

    }

    @Override
    public void renderRun() { // TASKS: MOVE
        System.out.println("I ran. E " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();

        if (currentAbility.equals(Abilities.MOVE)) {
            move();
        } else {
            System.err.println("ENTITY MISSING ACTION");
        }
    }

}
