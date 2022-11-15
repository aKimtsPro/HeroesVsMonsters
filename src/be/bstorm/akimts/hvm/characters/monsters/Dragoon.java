package be.bstorm.akimts.hvm.models;

import be.bstorm.akimts.hvm.tools.Tools;

public class Dragoon extends Monster implements ILootGold, ILootLeather {

    private final int gold;
    private final int leather;

    public Dragoon() {
        super(1, 0);
        this.gold = Tools.D6.cast();
        this.leather = Tools.D4.cast();
    }

    @Override
    public int getLeather() {
        return this.leather;
    }

    @Override
    public int getGold() {
        return this.gold;
    }
}
