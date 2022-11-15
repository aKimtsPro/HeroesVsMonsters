package be.bstorm.akimts.hvm.exceptions;

import be.bstorm.akimts.hvm.game.map.GameObject;

public class PlaceOccupiedException extends RuntimeException {

    private final GameObject object;

    public PlaceOccupiedException(GameObject object) {
        super(object.getPosition() + " is already occupied by " + object);
        this.object = object;
    }

    public GameObject getObject() {
        return object;
    }
}
