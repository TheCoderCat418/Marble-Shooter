package com.thecodercat418.marbleShooter;

public class Turn extends Entity {
    boolean isLeft;

    public Turn(Position tilepos, Direction directionStart, boolean isLeft) {
        super(tilepos, directionStart, TileType.LTURN);
        this.isLeft = isLeft;
    }

    boolean firstFrozen = true;

    @Override
    public void onFreeze() {
        if (!firstFrozen) {
            return;
        }
        int base = this.movingDirection.ordinal();
        if(isLeft){
            base--;
        }else{
            base++;
        }
        if(base>3){
            base-=4;
        }else if(base<0){
            base+=4;
        }

        
        super.setToMoveTo(Direction.values()[base]);
        
        super.frozen = false;
        firstFrozen = false;
    }

}
