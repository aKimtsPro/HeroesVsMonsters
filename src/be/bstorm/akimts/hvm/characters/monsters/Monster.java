package be.bstorm.akimts.hvm.characters.monsters;

import be.bstorm.akimts.hvm.characters.Character;

public abstract class Monster extends Character {

    public Monster(int bonusStamina, int bonusStrength, char representation) {
        super(bonusStamina, bonusStrength, representation);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " :" +
                "\n - STR: "+getStrength() +
                "\n - END: "+getStamina()+
                "\n - PV:  "+getHp()+"/"+getHpMax();
    }

}
