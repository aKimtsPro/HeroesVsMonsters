package be.bstorm.akimts.hvm.game.map;

import be.bstorm.akimts.hvm.exceptions.PlaceOccupiedException;
import be.bstorm.akimts.hvm.exceptions.PositionOutBoundsException;
import java.util.HashSet;
import java.util.Set;

public class Map {

    private final int height;
    private final int length;
    private final GameObject[][] map;
    private final Set<GameObject> gameObjects = new HashSet<>();

    public Map(int height, int length) {
        if( height <= 0 || length <= 0 )
            throw new IllegalArgumentException("height or length should not be <= 0");

        this.height = height;
        this.length = length;
        map = new GameObject[height][length];
    }

    private boolean isPlaceFree(int x, int y){
        return map[x][y] == null;
    }

    private GameObject get(int x, int y){
        if( x < 0 || y < 0 || y >= map.length || x >= map[y].length )
            throw new PositionOutBoundsException(x, y, this);

        return map[x][y];
    }

    private boolean place(int x, int y, GameObject gameObject){
        if( x < 0 || y < 0 || y >= map.length || x >= map[y].length )
            throw new PositionOutBoundsException(x, y, this);

        if(map[x][y] != null)
            throw new PlaceOccupiedException(map[x][y]);


        map[x][y] = gameObject;
        gameObject.setPosition(x,y);
        return gameObjects.add(gameObject);
    }


    private GameObject replace(int x, int y, GameObject gameObject){
        if( x < 0 || y < 0 || y >= map.length || x >= map[y].length )
            throw new PositionOutBoundsException(x, y, this);

        GameObject previous = get(x,y);
        remove(previous);
        place(x,y,gameObject);

        if( gameObject.getPosition() != null )
            remove( gameObject );

        return previous;
    }

    public boolean moveTo(GameObject toMove, int x, int y){
        if( x < 0 || y < 0 || y >= map.length || x >= map[y].length )
            throw new PositionOutBoundsException(x, y, this);

        if( toMove.getPosition() == null )
            throw new IllegalArgumentException("moved object should be placed");

        if( isPlaceFree( x, y) ) {
            remove( toMove );
            place(x, y, toMove);
            return true;
        }
        else
            return false;
    }

    public void remove(GameObject gameObject){
        if( gameObject == null || gameObject.getPosition() == null )
            throw new IllegalArgumentException("the objet should not be null and it should be placed");

        replace(gameObject.getPosition().getX(), gameObject.getPosition().getY(), null);
        gameObject.clearPosition();
        gameObjects.remove(gameObject);
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }


}
