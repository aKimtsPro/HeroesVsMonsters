package be.bstorm.akimts.hvm.exceptions;

public class IllegalMoveException extends RuntimeException{

    public IllegalMoveException() {
        super("Move would go behind the map");
    }
}
