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

    public boolean isPlaceFree(int x, int y){
        return map[x][y] == null;
    }

    public boolean isPlaceInvalid(int x, int y){
        return x < 0 || y < 0 || x >= map.length || y >= map[x].length;
    }
    public GameObject get(int x, int y){
        if(isPlaceInvalid(x, y))
            throw new PositionOutBoundsException(x, y, this);

        return map[x][y];
    }

    public boolean place(int x, int y, GameObject gameObject){

        if(isPlaceInvalid(x, y))
            throw new PositionOutBoundsException(x, y, this);

        if(map[x][y] != null)
            throw new PlaceOccupiedException(map[x][y]);


        map[x][y] = gameObject;
        if( gameObject != null ) {
            gameObject.setPosition(x, y);
            return gameObjects.add(gameObject);
        }
        else
            return false;
    }

    public boolean place(Position position, GameObject gameObject){
        if( position == null )
            throw new IllegalArgumentException( "Position cannot be null" );

        return place(position.getX(), position.getY(), gameObject);
    }


    private GameObject replace(int x, int y, GameObject gameObject){
        if(isPlaceInvalid(x, y))
            throw new PositionOutBoundsException(x, y, this);

        GameObject previous = get(x,y);
        remove(previous);
        place(x,y,gameObject);

        if( gameObject.getPosition() != null )
            remove( gameObject );

        return previous;
    }

    public boolean moveTo(GameObject toMove, int x, int y){
        if(isPlaceInvalid(x, y))
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

        map[gameObject.getPosition().getX()][gameObject.getPosition().getY()] = null;
        gameObject.clearPosition();
        gameObjects.remove(gameObject);
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("-".repeat(3*height + 2));
        sb.append('\n');
        for (int i = 0; i < length; i++) {

            sb.append('|');
            for (int j = 0; j < height; j++) {
                sb.append(' ');
                sb.append( map[i][j] == null ? '_' : map[i][j].getRep() );
                sb.append(' ');
            }
            sb.append('|');
            sb.append('\n');
        }
        sb.append("-".repeat(3*height + 2));
        sb.append('\n');

        return sb.toString();
    }
}
