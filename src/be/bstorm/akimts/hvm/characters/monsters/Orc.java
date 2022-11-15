package be.bstorm.akimts.hvm.characters.monsters;

import be.bstorm.akimts.hvm.tools.Tools;

public class Orc extends Monster implements ILootGold {


    public static final char representation = 'O';
    private final int gold;

    public Orc() {
        super(0, 1, representation);
        this.gold = Tools.D6.cast();
    }

    public int getGold() {
        return gold;
    }
}
