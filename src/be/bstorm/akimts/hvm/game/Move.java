package be.bstorm.akimts.hvm.game;

public enum Move {

    UP('u', -1, 0),
    RIGHT('r', 0, 1),
    DOWN('d', 1, 0),
    LEFT('l', 0,-1);

    private final char cmd;
    private final int moveX;
    private final int moveY;

    Move(char cmd, int mouveX, int mouveY) {
        this.cmd = cmd;
        this.moveX = mouveX;
        this.moveY = mouveY;
    }

    public char getCmd() {
        return cmd;
    }

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public static boolean isValidCommand(char cmd){
        return cmd == 'u' || cmd == 'r' || cmd == 'd' || cmd == 'l';
    }

    public static Move fromCmd(char cmd){
        return switch (cmd){
            case 'u' -> UP;
            case 'd' -> DOWN;
            case 'l' -> LEFT;
            case 'r' -> RIGHT;
            default -> null;
        };
    }
}
