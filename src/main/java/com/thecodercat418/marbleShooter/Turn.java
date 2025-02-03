package com.thecodercat418.marbleShooter;

public class Turn extends Entity {
    Direction afterFreeze;

    public Turn(Position tilepos, Direction directionStart, Direction afterFreeze) {
        super(tilepos, directionStart, TileType.LTURN);
        this.afterFreeze = afterFreeze;
    }

    boolean firstFrozen = true;

    @Override
    public void onFreeze() {
        if (!firstFrozen) {
            return;
        }


        
        super.setToMoveTo(afterFreeze);
        
        super.frozen = false;
        firstFrozen = false;
    }

}
