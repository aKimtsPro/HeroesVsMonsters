package be.bstorm.akimts.hvm.models;

import be.bstorm.akimts.hvm.tools.Tools;

public class Wolf extends Monster implements ILootLeather {

    private final int leather;

    public Wolf() {
        super(0, 0);
        this.leather = Tools.D4.cast();
    }

    @Override
    public int getLeather() {
        return 0;
    }
}
