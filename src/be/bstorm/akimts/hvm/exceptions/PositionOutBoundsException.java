package be.bstorm.akimts.hvm.exceptions;

import be.bstorm.akimts.hvm.game.map.Map;

public class PositionOutBoundsException extends RuntimeException {


    private final int x;
    private final int y;
    private final Map map;

    public PositionOutBoundsException(int x, int y , Map map) {
        super("{"+x+";"+y+"} position is out of bound(length : "+map.getLength()+", height : "+map.getHeight()+")");
        this.map = map;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Map getMap() {
        return map;
    }
}
