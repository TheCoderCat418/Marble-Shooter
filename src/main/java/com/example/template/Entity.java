package com.example.template;


public class Entity extends Tile{
    private Position toMoveTo = new Position(7, 7);
    private Position cPosition = new Position(0, 0);

    private int clkdvsr = 50;
    private int clkcnt = 0;

    private long a = 0;


    public Entity(Position cPosition, Direction direction){
        super(cPosition, TileType.ENTITY);
        Position indexPos = new Position(cPosition.x, cPosition.y);
        while (true) {
            if(!Render.tileMap[indexPos.x][indexPos.y].tt.equals(TileType.EMPTY)){
                break;
            }
            
        }
        //TODO: WORK ON
        //toMoveTo = new Position(, );
    }
    
    public void renderRun(){ //TASKS: MOVE
        System.out.println("I ran. E " + (System.currentTimeMillis()-a));
        a = System.currentTimeMillis();
        Position oldPos = new Position(cPosition.x, cPosition.y);

        if(cPosition.x < toMoveTo.x){
            cPosition.x++;
        }else if(cPosition.x > toMoveTo.x){
            cPosition.x--;
        }
        if(cPosition.y < toMoveTo.y){
            cPosition.y++;
        }else if(cPosition.y > toMoveTo.y){
            cPosition.y--;
        }
        System.out.println(cPosition);
        System.out.println(oldPos);
        if(cPosition.x != toMoveTo.x || cPosition.y != toMoveTo.y){
            Render.tileMap[cPosition.x][cPosition.y] = Render.tileMap[oldPos.x][oldPos.y];
            Render.tileMap[oldPos.x][oldPos.y] = new Tile(oldPos, TileType.EMPTY);
        }
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
