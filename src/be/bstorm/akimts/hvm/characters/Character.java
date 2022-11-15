package be.bstorm.akimts.hvm.characters;

import be.bstorm.akimts.hvm.game.map.GameObject;
import be.bstorm.akimts.hvm.tools.Tools;

public abstract class Character extends GameObject {

    private final int stamina;
    private final int strength;
    private final int hpMax;

    private final int bonusStamina;
    private final int bonusStrength;

    private int hp;

    public Character(int bonusStamina, int bonusStrength, char representation){
        super(representation);
        this.bonusStamina = bonusStamina;
        this.bonusStrength = bonusStrength;
        this.stamina = Tools.best3Of4();
        this.strength = Tools.best3Of4();
        this.hpMax = getStamina() + Tools.modBasedOn( getStamina() );
        this.hp = this.hpMax;
    }


    public int hit(Character character){
        int dmg = Tools.D4.cast() + Tools.modBasedOn( this.getStrength() );
        if(character.hp < dmg )
            return character.hp - (character.hp = 0);
        else
            return character.hp - (character.hp -= dmg);
    }

    public void heal(){
        this.hp = this.hpMax;
    }

    public int getStamina() {
        return stamina + bonusStamina;
    }

    public int getStrength() {
        return strength + bonusStrength;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getHp() {
        return hp;
    }


}
