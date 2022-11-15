package be.bstorm.akimts.hvm.game;

import be.bstorm.akimts.hvm.exceptions.PlacementOutBoundsException;

public class Map {

    private final int height;
    private final int length;
    private final GameObject[][] map;

    public Map(int height, int length) {
        if( height <= 0 || length <= 0 )
            throw new IllegalArgumentException("hieght or length should not be <= 0");

        this.height = height;
        this.length = length;
        map = new GameObject[height][length];
    }

    private boolean isPlaceFree(int x, int y){
        return map[x][y] == null;
    }

    private boolean place(int x, int y, GameObject gameObject){

        if( x < 0 || y < 0 || y >= map.length || x >= map[y].length )
            throw new PlacementOutBoundsException(x, y, this);

        map[x][y] = gameObject;
        gameObject.setPosition(x,y);

    }

    private GameObject replace(int x, int y, GameObject gameObject){
        if( x < 0 || y < 0 || y >= map.length || x >= map[y].length )
            throw new PlacementOutBoundsException(x, y, this);

        GameObject previous = map[x][y];
        map[x][y] = gameObject;
        return previous;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }
}
