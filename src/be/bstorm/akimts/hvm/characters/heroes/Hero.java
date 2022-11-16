package be.bstorm.akimts.hvm.characters.heroes;

import be.bstorm.akimts.hvm.characters.Character;
import be.bstorm.akimts.hvm.characters.monsters.ILootGold;
import be.bstorm.akimts.hvm.characters.monsters.ILootLeather;
import be.bstorm.akimts.hvm.characters.monsters.Monster;

public abstract class Hero extends Character {

    public static final char representation = 'H';
    private int gold;
    private int leather;
    private final String name;

    public Hero(String name,int bonusStamina, int bonusStrength) {
        super(bonusStamina, bonusStrength, representation);
        this.name = name;
    }

    public void loot(Monster monster){
        if( monster instanceof ILootGold )
            this.gold += ((ILootGold) monster).getGold();
        if( monster instanceof ILootLeather )
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


    public int getLeather() {
        return leather;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName() + " :" +
                "\n - RACE: " + getClass().getSimpleName() +
                "\n - STR: "+getStrength() +
                "\n - END: "+getStamina()+
                "\n - PV:  "+getHp()+"/"+getHpMax() +
                "\n - GOLD: "+ getGold() + " | LEATHER: " + getLeather();
    }
}
