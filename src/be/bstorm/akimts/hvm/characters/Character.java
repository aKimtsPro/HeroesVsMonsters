package be.bstorm.akimts.hvm.characters;

import be.bstorm.akimts.hvm.game.map.GameObject;
import be.bstorm.akimts.hvm.tools.Tools;

import java.util.function.BiConsumer;

public abstract class Character extends GameObject{

    private final int stamina;
    private final int strength;
    private final int hpMax;
    private int hp;

    private final int bonusStamina;
    private final int bonusStrength;

    private BiConsumer<Character, Integer> hitSubscription = (character, dmg) -> {};


    public Character(int bonusStamina, int bonusStrength, char representation){
        super(representation);
        this.bonusStamina = bonusStamina;
        this.bonusStrength = bonusStrength;
        this.stamina = Tools.best3Of4();
        this.strength = Tools.best3Of4();
        this.hpMax = getStamina() + Tools.modBasedOn( getStamina() );
        this.hp = this.hpMax;
    }


    public void hit(Character character){
        if(character == null)
            throw new IllegalArgumentException("opponent should not be null");

        int dmg = Tools.D4.cast() + Tools.modBasedOn( this.getStrength() );
        character.isHit( dmg );
    }

    public void heal(){
        this.hp = this.hpMax;
    }

    public int getStamina() { // TODO separate into get (stamina, bonus, total)
        return stamina + bonusStamina;
    }

    public int getStrength() { // TODO separate into get (strength, bonus, total)
        return strength + bonusStrength;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Character{" +
                "stamina=" + stamina +
                ", strength=" + strength +
                ", hpMax=" + hpMax +
                ", bonusStamina=" + bonusStamina +
                ", bonusStrength=" + bonusStrength +
                ", hp=" + hp +
                '}';
    }

    private void isHit(int dmg){
        hp = dmg > hp ? 0 : hp - dmg;
        hitSubscription.accept( this, dmg );
    }

    public void setHitSubscription(BiConsumer<Character, Integer> hitSubscription) {
        if( hitSubscription != null )
            this.hitSubscription = hitSubscription;
    }
}
