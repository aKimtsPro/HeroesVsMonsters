package be.bstorm.akimts.hvm.models;

public abstract class Hero extends Character {

    private int gold;
    private int leather;

    public Hero(int bonusStamina, int bonusStrength) {
        super(bonusStamina, bonusStrength);
    }

    public void loot(Monster monster){
        if( monster instanceof ILootGold )
            this.gold += ((ILootGold) monster).getGold();
        if( monster instanceof  ILootLeather )
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
