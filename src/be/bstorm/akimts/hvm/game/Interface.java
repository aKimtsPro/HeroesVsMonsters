package be.bstorm.akimts.hvm.game;

import be.bstorm.akimts.hvm.characters.monsters.Monster;

import java.util.Scanner;

public class Interface {

    private final Game game = new Game();
    private final Scanner scanner = new Scanner( System.in );

    public Interface(){
        setupReactions();
    }

    public void start(){

        System.out.println("The game begins, you are: " + game.getHero().getName());
        char cmd;
        do {
            System.out.println( game.getHero() );
            System.out.println( game.getMapRepresentation() );
            System.out.print("""
                                
                                -----------
                                What to do?
                                u - move up
                                d - move down
                                l - move left
                                r - move right
                                q - quit
                                -----------
                                >
                               """);
            cmd = scanner.nextLine().toLowerCase().charAt(0);
            if( Move.isValidCommand(cmd)  ){
                Move move =  Move.fromCmd( cmd );
                if( !game.isMoveIllegal(move) ) {
                    Monster m = game.move( move );
                    if (m != null) {
                        System.out.println("You come across a monster : " + m);
                        if (game.getHero().fight(m)) { // TODO bouger la logique de combat dans Game
                            System.out.println("\nYou are victorious !!\n");

                            lootingProcess(m);
                            game.removeKilled();

                            System.out.println("\nYou heal !!\n");
                            game.getHero().heal();
                        } else {
                            System.out.println("You died :'(\n");
                        }
                    }
                }
                else {
                    System.out.println("You cannot move out of Shorewood");
                }
            }


        } while ( cmd != 'q' &&  game.getHero().getHp() > 0 && game.getMonstersLeft() > 0 );

        if( game.getMonstersLeft() <= 0 )
            System.out.println("You vanquish all the monster of Shorewood");

        System.out.println("Game Over");

    }


    private void lootingProcess(Monster monster){
        System.out.println("You loot the monster!!");

        int goldDiff = -game.getHero().getGold();
        int leatherDiff = -game.getHero().getLeather();
        game.getHero().loot( monster );
        goldDiff += game.getHero().getGold();
        leatherDiff += game.getHero().getLeather();

        System.out.println("You have now:");
        System.out.println("- gold: " + game.getHero().getGold() + "(+" + goldDiff +")");
        System.out.println("- leather: " + game.getHero().getLeather() + "(+" + leatherDiff +")");
    }

    private void setupReactions(){
        game.setHeroHitReaction( (charac, dmg) -> System.out.println("Your Hero takes " + dmg + " damages. He's at " + charac.getHp() + "/" + charac.getHpMax() + " HP" ) );
        game.setMonstersHitReaction( (charac, dmg) -> System.out.println("The monster takes " + dmg + " damages. He's at " + charac.getHp() + "/" + charac.getHpMax() + " HP" ) );
    }

}
