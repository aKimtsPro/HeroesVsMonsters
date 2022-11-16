package be.bstorm.akimts.hvm.game.map;

public class GameObject {

    private Position position;
    private final char representation;

    public GameObject(char representation) {
        this.representation = representation;
    }

    public GameObject(int positionX, int positionY, char representation) {
        this.position = Position.of(positionX, positionY);
        this.representation = representation;
    }

    void setPosition(int x, int y){
        this.position = Position.of(x,y);
    }

    public char getRep() {
        return representation;
    }

    public Position getPosition() {
        return position;
    }

    void clearPosition(){
        this.position = null;
    }
}
