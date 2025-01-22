package com.example.template;

public class BlueBug {
    private int x;
    private int y;
    private long startTime;
    public  BlueBug(int x, int y, int[][] board){
        this.x = x;
        this.y = y;
        board[x][y] = 3;
        startTime = System.nanoTime();
    }

    public void changeLocation(int[][] board){
        int setx = x;
        int sety = y;
        int num = (int)(Math.random()*9);
        switch (num) {
            case 0: //STAY
                break;
            case 1: //UP
                sety++;
                break;
            case 2: //DOWN
                sety--;
                break;
            case 3: //LEFT
                setx--;
                break;
            case 4: //RIGHT
                setx++;
                break;
                
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                break;
        }


        if(setx>board.length-1||setx<0){
            return;
        }
        if(sety>board.length-1||sety<0){
            return;
        }
        if(board[setx][sety] == 1){
            return;
        }
        
        
        board[setx][sety] =3;
        board[x][y]=0;
        x = setx;
        y = sety;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(x+i>board.length-1||x+i<0){
                    continue;
                }
                if(y+j>board.length-1||y+j<0){
                    continue;
                }
                if(board[x+i][y+j] == 2){
                    System.out.println("Bugged");;
                }
            }
        }

    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime() {
        this.startTime = System.nanoTime();
    }
}
