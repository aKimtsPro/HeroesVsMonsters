package be.bstorm.akimts.hvm.game;

import be.bstorm.akimts.hvm.characters.Character;
import be.bstorm.akimts.hvm.characters.heroes.Hero;
import be.bstorm.akimts.hvm.characters.monsters.Dragoon;
import be.bstorm.akimts.hvm.characters.monsters.Monster;
import be.bstorm.akimts.hvm.characters.monsters.Orc;
import be.bstorm.akimts.hvm.characters.monsters.Wolf;
import be.bstorm.akimts.hvm.exceptions.IllegalMoveException;
import be.bstorm.akimts.hvm.game.map.GameObject;
import be.bstorm.akimts.hvm.game.map.Map;
import be.bstorm.akimts.hvm.game.map.Position;
import be.bstorm.akimts.hvm.tools.Tools;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class Game {

    private final Map map = new Map(15,15);
    private final Set<Monster> monsters = new HashSet<>();
    private final Hero hero = Tools.getRandomHero();

    public Game() {
        setupMonsters();
        setupMap();
    }

    private void setupMonsters(){
        monsters.add( new Wolf() );
        monsters.add( new Wolf() );
        monsters.add( new Wolf() );
        monsters.add( new Wolf() );
        monsters.add( new Wolf() );
        monsters.add( new Orc() );
        monsters.add( new Orc() );
        monsters.add( new Orc() );
        monsters.add( new Dragoon() );
        monsters.add( new Dragoon() );
    }

    private void setupMap(){

        for (Monster monster : monsters) {
            map.place( generateValidPosition() , monster );
        }
        map.place( generateValidPosition(), hero );

    }

    private Position generateValidPosition(){

        Random rdm = new Random();
        int x, y;
        boolean placeOk;
        do {
            placeOk = true;
            x = rdm.nextInt(map.getHeight());
            y = rdm.nextInt(map.getLength());
            for (int i = -2; i <= 2; i++) {
                if(
                    (!map.isPlaceInvalid(x+i, y) && !map.isPlaceFree(x+i, y))
                    || (!map.isPlaceInvalid(x, y+i) && !map.isPlaceFree(x, y+i))
                ){
                    placeOk = false;
                    break;
                }
            }
        } while( !placeOk );

        return Position.of(x,y);
    }

    public String getMapRepresentation(){
        return map.toString();
    }

    public boolean isMoveIllegal(Move move){
        int nextX = hero.getPosition().getX() + move.getMoveX();
        int nextY = hero.getPosition().getY() + move.getMoveY();

        return map.isPlaceInvalid( nextX, nextY );
    }

    public Monster move(Move move){

        int nextX = hero.getPosition().getX() + move.getMoveX();
        int nextY = hero.getPosition().getY() + move.getMoveY();

        if( map.isPlaceInvalid( nextX, nextY ) )
            throw new IllegalMoveException();

        GameObject gameObject = map.get( nextX, nextY );
        if( gameObject == null ){
            map.moveTo(hero, nextX, nextY);
        }

        return (Monster) gameObject;

    }

    public void removeKilled(){
        Monster toRemove = null;
        for (Monster monster : monsters) {
            if(monster.getHp() <= 0) {
                map.remove(monster);
                toRemove = monster;
                break;
            }
        }
        monsters.remove( toRemove );
    }

    public int getMonstersLeft(){
        return monsters.size();
    }

    public Hero getHero(){
        return this.hero;
    }

    public void setHeroHitReaction(BiConsumer<Character, Integer> reaction){
        hero.setHitSubscription( reaction );
    }

    public void setMonstersHitReaction(BiConsumer<Character, Integer> reaction){
        monsters.forEach( m -> m.setHitSubscription( reaction ) );
    }


}
