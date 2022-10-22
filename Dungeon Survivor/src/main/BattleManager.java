package main;

import java.util.Random;

import entity.Entity;

public class BattleManager {

    GamePanel gp;
    
    Random randomGen;
    public int monsterIndex;
    // state 0 = find who start, state 1 = player turn, state 2 = monster turn, state 3 = simultaneous, state 4 = heal after battle
    int state;
    boolean rolling;
    int diceCounter;
//    int[] oldDice;
    
    public BattleManager(GamePanel gp) {
        this.gp = gp;
        randomGen = new Random();
//        oldDice = new int[2];
    }
    
    void setBattle() {
        gp.player.changeFacing();
        gp.player.steps--;
        gp.player.moving = true;
        gp.player.spriteNum = 1;
        gp.state = gp.BATTLE;
        state = 0;
        rolling = false;
    }
    
    void endBattle() {
        setBattle();

//        gp.player.dice[0] = oldDice[0];
//        gp.player.dice[1] = oldDice[1];
        gp.player.state = 0;
        gp.state = gp.MOVE;
        gp.monstersM.monsters[monsterIndex] = null;
    }
    
    public void update() {
        if(gp.state == gp.MOVE) {
            for(int i = 0; i < gp.monstersM.monsters.length; i++) {
                if(gp.monstersM.monsters[i] != null && gp.player.x == gp.monstersM.monsters[i].x && gp.player.y == gp.monstersM.monsters[i].y) {
                    monsterIndex = i;
                    gp.monstersM.monsters[monsterIndex].visable = true;
                    switch (gp.player.direction){
                        case Entity.UP:
                            gp.monstersM.monsters[monsterIndex].direction = Entity.DOWN;
                            break;
                        case Entity.DOWN:
                            gp.monstersM.monsters[monsterIndex].direction = Entity.UP;
                            break;
                        case Entity.LEFT:
                            gp.monstersM.monsters[monsterIndex].direction = Entity.RIGHT;
                            break;
                        case Entity.RIGHT:
                            gp.monstersM.monsters[monsterIndex].direction = Entity.LEFT;
                            break;
                    }
//                    oldDice[0] = gp.player.dice[0];
//                    oldDice[1] = gp.player.dice[1];
                    setBattle();
                    break;
                }
            }
        }
        else if(gp.state == gp.BATTLE) {
           
            if(rolling) {
                if(diceCounter < 50) {
                    if(state == 0) {
                        gp.player.dice[0] = randomGen.nextInt(6);
                        gp.monstersM.monsters[monsterIndex].dice[0] = randomGen.nextInt(6);
                    }
                    else if(state == 1 || state == 4) {
                        gp.player.dice[0] = randomGen.nextInt(6);
                        gp.player.dice[1] = randomGen.nextInt(6);   
                    }
                    else if(state == 2) {
                        gp.monstersM.monsters[monsterIndex].dice[0] = randomGen.nextInt(6);
                        gp.monstersM.monsters[monsterIndex].dice[1] = randomGen.nextInt(6);
                    }
                    else if(state == 3) {
                        gp.player.dice[0] = randomGen.nextInt(6);
                        gp.player.dice[1] = randomGen.nextInt(6); 
                        
                        gp.monstersM.monsters[monsterIndex].dice[0] = randomGen.nextInt(6);
                        gp.monstersM.monsters[monsterIndex].dice[1] = randomGen.nextInt(6);
                    }
                    diceCounter++;
                }
                else if(state == 0) {
                    if(gp.player.dice[0] > gp.monstersM.monsters[monsterIndex].dice[0]) {
                        state = 1;
                    } 
                    else if(gp.player.dice[0] < gp.monstersM.monsters[monsterIndex].dice[0])
                        state = 2;
                    else state = 3;
                    rolling = false;
                    diceCounter = 0;
                }
                else if(state == 1) {
                    int damage = (gp.player.dice[0]+1)*10 + gp.player.dice[1]+1;
                    if(gp.player.dice[0] == gp.player.dice[1])
                        damage += 100;
                    gp.monstersM.monsters[monsterIndex].hit_point -= damage;
                    if(gp.monstersM.monsters[monsterIndex].hit_point <= 0) {
                        gp.monstersM.monsters[monsterIndex].hit_point = 0;
                        state = 4;
                    }
                    else state = 2; 
                    rolling = false;
                    diceCounter = 0;
                }
                else if(state == 2) {
                    int damage = (gp.monstersM.monsters[monsterIndex].dice[0]+1)*10 + gp.monstersM.monsters[monsterIndex].dice[1]+1;
                    gp.player.hit_point -= damage;
                    if(gp.monstersM.monsters[monsterIndex].symbol == Entity.ZORK && gp.monstersM.monsters[monsterIndex].dice[0] == gp.monstersM.monsters[monsterIndex].dice[1])
                            damage += 100;
                    if(gp.player.hit_point <= 0) {
                        gp.player.hit_point = 0;
                        gp.gameThread = null;
                    }
                    else state = 1; 
                    rolling = false;
                    diceCounter = 0;
                }
                else if(state == 3) {
                    // player damage
                    int damage = (gp.player.dice[0]+1)*10 + gp.player.dice[1]+1;
                    if(gp.player.dice[0] == gp.player.dice[1])
                        damage += 100;
                    gp.monstersM.monsters[monsterIndex].hit_point -= damage;
                    
                    if(gp.monstersM.monsters[monsterIndex].hit_point <= 0)
                        gp.monstersM.monsters[monsterIndex].hit_point = 0;
                    
                    // monster damage
                    damage = (gp.monstersM.monsters[monsterIndex].dice[0]+1)*10 + gp.monstersM.monsters[monsterIndex].dice[1]+1;
                    if(gp.monstersM.monsters[monsterIndex].symbol == Entity.ZORK && gp.monstersM.monsters[monsterIndex].dice[0] == gp.monstersM.monsters[monsterIndex].dice[1])
                        damage += 100;
                    gp.player.hit_point -= damage;
                    
                    if(gp.player.hit_point <= 0) {
                        gp.player.hit_point = 0;
                        gp.gameThread = null;
                    }
                    else if(gp.monstersM.monsters[monsterIndex].hit_point <= 0) {
                        gp.monstersM.monsters[monsterIndex].hit_point = 0;
                        state = 4;
                    }
                    rolling = false;
                    diceCounter = 0;
                }
                else if(state == 4) {
                    int heal = (gp.player.dice[0]+1)*10 + gp.player.dice[1]+1;
                    gp.player.hit_point += heal;
                    rolling = false;
                    diceCounter = 0;
                    endBattle();
                }
            }
            else {
                if(state == 0) {
                    if(gp.keyH.rPressed) {
                        rolling = true;
                    }
                }
                else if(state == 1 || state == 3 || state == 4) {
                    if(gp.keyH.rPressed) {
                        rolling = true;
                    }
                }
                else if(state == 2) {
                   rolling = true;
                }
            }
        }
    }
}