package be.bstorm.akimts.hvm.characters.monsters;

import be.bstorm.akimts.hvm.tools.Tools;

public class Wolf extends Monster implements ILootLeather {

    private static final char representation = 'W';
    private final int leather;

    public Wolf() {
        super(0, 0, representation);
        this.leather = Tools.D4.cast();
    }

    @Override
    public int getLeather() {
        return leather;
    }
}
