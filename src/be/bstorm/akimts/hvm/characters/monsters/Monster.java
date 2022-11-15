package be.bstorm.akimts.hvm.models;

import be.bstorm.akimts.hvm.models.Character;

public abstract class Monster extends Character {

    public Monster(int bonusStamina, int bonusStrength) {
        super(bonusStamina, bonusStrength);
    }

}
