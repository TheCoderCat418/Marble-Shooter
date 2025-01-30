package com.thecodercat418.marbleShooter;

public class Entity extends Tile {
    public Position toMoveTo;
    public Abilities currentAbility;
    public long a = 0;

    public Entity(Position tilepos, Direction direction, TileType t) {
        super(tilepos, t);
        super.clkdvsr = 15;
        System.out.println(tilepos);
        this.tilepos = tilepos;
        if (this.getClass() == Entity.class) { // If the class is being extended, do not set up the toMoveTo varible.
                                               // Allow the child constructor to do that.
            setToMoveTo(direction);
        }
        // TODO: WORK ON
        // toMoveTo = new Position(, );
    }

    public void setToMoveTo(Direction direction){
        Position indexPos = new Position(tilepos.x, tilepos.y);
            while (true) {
                if (!Render.tileMap[indexPos.x][indexPos.y].tt.equals(TileType.EMPTY)) {
                    toMoveTo = indexPos;
                    break;
                }
                switch (direction) {
                    case UP:
                        indexPos.y--;
                        break;
                    case DOWN:
                        indexPos.y++;
                        break;
                    case LEFT:
                        indexPos.x--;
                        break;
                    case RIGHT:
                        indexPos.x++;
                        break;
                    default:
                        break;
                }
            }
        currentAbility = Abilities.MOVE;
    }

    public void move(){
        Position oldPos = new Position(tilepos.x, tilepos.y);

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
        System.out.println(tilepos);
        System.out.println(oldPos);
        if (tilepos.x != toMoveTo.x || tilepos.y != toMoveTo.y) {
            Render.tileMap[tilepos.x][tilepos.y] = Render.tileMap[oldPos.x][oldPos.y];
            Render.tileMap[oldPos.x][oldPos.y] = new Tile(oldPos, TileType.EMPTY);
            return;
        }
        frozen = true;
    }

    @Override
    public void renderRun() { // TASKS: MOVE
        System.out.println("I ran. E " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        
        if(currentAbility.equals(Abilities.MOVE)){
            move();
        } else {
            System.err.println("ENTITY MISSING ACTION");
        }
    }

    // public void clockDivider(){
    // System.out.println("E RUN");
    // if(clkdvsr <= 1){
    // renderRun();
    // return;
    // }

    // if(clkdvsr > clkcnt){
    // clkcnt++;
    // }else{
    // renderRun();
    // clkcnt = 0;
    // return;
    // }
    // }

}
