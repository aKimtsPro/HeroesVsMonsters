package be.bstorm.akimts.hvm.tools;

public class Dice {

    private final int min;
    private final int max;

    public Dice(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int cast(){
        return min + (int) (Math.random()*max);
    }
}
