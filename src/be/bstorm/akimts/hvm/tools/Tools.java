package be.bstorm.akimts.hvm.tools;

import be.bstorm.akimts.hvm.characters.heroes.Dwarf;
import be.bstorm.akimts.hvm.characters.heroes.Hero;
import be.bstorm.akimts.hvm.characters.heroes.Human;

public abstract class Tools {

    private Tools(){}

    private static final String[] heroesNames = {
            "Francois I",
            "Francois II",
            "Huong",
            "Liva",
            "Tibault",
            "Thomas",
            "Yann",
            "Bryan",
            "Mathis",
            "Felix",
            "Tom",
            "Florent"
    };

    public final static Dice D4 = new Dice(1,4);
    public final static Dice D6 = new Dice(1,6);

    public static int best3Of4(){
        int sum = 0;
        int min = 7;

        for (int i = 0; i < 4; i++) {
            int rslt = D6.cast();
            sum += rslt;
            if( min > rslt )
                min = rslt;
        }

        return sum - min;
    }

    public static int modBasedOn(int stat){
        if ( stat <  5 ) return -1;
        if ( stat < 10 ) return 0;
        if ( stat < 15 ) return 1;
        return 2;
    }


    public static Hero getRandomHero(){

        String name = heroesNames[ (int)(Math.random()*12) ];

        int rdmized = (int)(Math.random()*2);
        if( rdmized == 0 ){
            return new Human(name);
        }
        else {
            return new Dwarf(name);
        }

    }

}
