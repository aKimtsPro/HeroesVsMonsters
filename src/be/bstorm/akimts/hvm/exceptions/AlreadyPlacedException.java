package be.bstorm.akimts.hvm.exceptions;

import be.bstorm.akimts.hvm.game.map.GameObject;

public class AlreadyPlacedException extends RuntimeException{

    private final GameObject gameObject;

    public AlreadyPlacedException(GameObject gameObject) {
        super(gameObject + " is already placed in this map");
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

}
