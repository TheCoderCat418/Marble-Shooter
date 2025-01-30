package com.example.template;


public class Entity extends Tile{
    private Position toMoveTo = new Position(7, 7);

    private int clkdvsr = 50;
    private int clkcnt = 0;

    private long a = 0;


    public Entity(Position tilepos, Direction direction, TileType t){
        super(tilepos, t);
        System.out.println(tilepos);
        this.tilepos = tilepos;
        Position indexPos = new Position(tilepos.x, tilepos.y);
        while (true) {
            if(!Render.tileMap[indexPos.x][indexPos.y].tt.equals(TileType.EMPTY)){
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
        //TODO: WORK ON
        //toMoveTo = new Position(, );
    }
    
    public void renderRun(){ //TASKS: MOVE
        System.out.println("I ran. E " + (System.currentTimeMillis()-a));
        a = System.currentTimeMillis();
        Position oldPos = new Position(tilepos.x, tilepos.y);

        if(tilepos.x < toMoveTo.x){
            tilepos.x++;
        }else if(tilepos.x > toMoveTo.x){
            tilepos.x--;
        }
        if(tilepos.y < toMoveTo.y){
            tilepos.y++;
        }else if(tilepos.y > toMoveTo.y){
            tilepos.y--;
        }
        System.out.println(tilepos);
        System.out.println(oldPos);
        if(tilepos.x != toMoveTo.x || tilepos.y != toMoveTo.y){
            Render.tileMap[tilepos.x][tilepos.y] = Render.tileMap[oldPos.x][oldPos.y];
            Render.tileMap[oldPos.x][oldPos.y] = new Tile(oldPos, TileType.EMPTY);
            return;
        }
        frozen = true;
    }
    
    public void clockDivider(){
        System.out.println("E RUN");
        if(clkdvsr <= 1){
            renderRun();
            return;
        }

        if(clkdvsr > clkcnt){
            clkcnt++;
        }else{
            renderRun();
            clkcnt = 0;
            return;
        }
    }

    
}
