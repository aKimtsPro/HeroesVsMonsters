package be.bstorm.akimts.hvm.models;

import be.bstorm.akimts.hvm.tools.Tools;

public class Orc extends Monster implements ILootGold {

    private final int gold;

    public Orc() {
        super(0, 1);
        this.gold = Tools.D6.cast();
    }

    public int getGold() {
        return gold;
    }
}
