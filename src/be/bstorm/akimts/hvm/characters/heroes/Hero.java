package be.bstorm.akimts.hvm.characters.heroes;

import be.bstorm.akimts.hvm.characters.Character;
import be.bstorm.akimts.hvm.characters.monsters.ILootGold;
import be.bstorm.akimts.hvm.characters.monsters.ILootLeather;
import be.bstorm.akimts.hvm.characters.monsters.Monster;

public abstract class Hero extends Character {

    public static final char representation = 'H';
    private int gold;
    private int leather;

    public Hero(int bonusStamina, int bonusStrength) {
        super(bonusStamina, bonusStrength, representation);
    }

    public void loot(Monster monster){
        if( monster instanceof ILootGold)
            this.gold += ((ILootGold) monster).getGold();
        if( monster instanceof ILootLeather)
            this.leather += ((ILootLeather) monster).getLeather();
    }

    public boolean fight(Monster monster){

        while ( this.getHp() > 0 && monster.getHp() > 0 ){
            this.hit( monster );
            if( monster.getHp() > 0 )
                monster.hit( this );
        }

        return this.getHp() > 0;

    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLeather() {
        return leather;
    }

    public void setLeather(int leather) {
        this.leather = leather;
    }
}
