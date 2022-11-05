package main;

import java.util.Random;

import entity.Entity;

public class BattleManager {

    GamePanel gp;
    
    Random randomGen;
    public int monsterIndex;
    // state 0 = find who start, state 1 = player turn, state 2 = monster turn, state 3 = simultaneous, state 4 = heal after battle
    int battleState;
    boolean rolling;
    int diceCounter;
    int endGameCounter;
    
    public BattleManager(GamePanel gp) {
        this.gp = gp;
        randomGen = new Random();
    }
    
    void setBattle() {
        gp.player.changeFacing();
        gp.player.steps--;
        gp.player.forcedMove = true;
        gp.player.gameState = gp.player.BATTLE;
        battleState = 0;
        rolling = false;
    }
    
    void endBattle() {
        if(gp.monstersM.monsters[monsterIndex].symbol == Entity.ZORK) {
            gp.panelState = gp.END;
            gp.es.setTitleTexts("You Defeated Zork!!\nNumber of steps: " + ++gp.player.steps);
            return;
        }
        setBattle();
        gp.player.movingState = 0;
        gp.player.gameState = gp.player.MOVE;
        gp.monstersM.monster_remaning--;
        rolling = false;
        diceCounter = 0; 
        gp.monstersM.monsters[monsterIndex] = null;            
    }
    
    public void update() {
        if(gp.player.gameState == gp.player.MOVE) {
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
                    setBattle();
                    break;
                }
            }
        }
        else if(gp.player.gameState == gp.player.BATTLE) {
           
            if(rolling) {
                if(diceCounter < 50) {
                    if(battleState == 0) {
                        gp.player.dice[0] = randomGen.nextInt(6);
                        gp.monstersM.monsters[monsterIndex].dice[0] = randomGen.nextInt(6);
                    }
                    else if(battleState == 1 || battleState == 4) {
                        gp.player.dice[0] = randomGen.nextInt(6);
                        gp.player.dice[1] = randomGen.nextInt(6);   
                    }
                    else if(battleState == 2) {
                        gp.monstersM.monsters[monsterIndex].dice[0] = randomGen.nextInt(6);
                        gp.monstersM.monsters[monsterIndex].dice[1] = randomGen.nextInt(6);
                    }
                    else if(battleState == 3) {
                        gp.player.dice[0] = randomGen.nextInt(6);
                        gp.player.dice[1] = randomGen.nextInt(6); 
                        
                        gp.monstersM.monsters[monsterIndex].dice[0] = randomGen.nextInt(6);
                        gp.monstersM.monsters[monsterIndex].dice[1] = randomGen.nextInt(6);
                    }
                    diceCounter++;
                }
                else if(battleState == 0) {
                    if(gp.player.dice[0] > gp.monstersM.monsters[monsterIndex].dice[0]) {
                        battleState = 1;
                    } 
                    else if(gp.player.dice[0] < gp.monstersM.monsters[monsterIndex].dice[0])
                        battleState = 2;
                    else battleState = 3;
                    rolling = false;
                    diceCounter = 0;
                }
                else if(battleState == 1) {
                    int damage = (gp.player.dice[0]+1)*10 + gp.player.dice[1]+1;
                    if(gp.player.dice[0] == gp.player.dice[1])
                        damage += 100;
                    gp.monstersM.monsters[monsterIndex].hit_point -= damage;
                    if(gp.monstersM.monsters[monsterIndex].hit_point <= 0) {
                        gp.monstersM.monsters[monsterIndex].hit_point = 0;
                        battleState = 4;
                    }
                    else battleState = 2; 
                    rolling = false;
                    diceCounter = 0;
                }
                else if(battleState == 2) {
                    int damage = (gp.monstersM.monsters[monsterIndex].dice[0]+1)*10 + gp.monstersM.monsters[monsterIndex].dice[1]+1;
                    gp.player.hit_point -= damage;
                    if(gp.monstersM.monsters[monsterIndex].symbol == Entity.ZORK && gp.monstersM.monsters[monsterIndex].dice[0] == gp.monstersM.monsters[monsterIndex].dice[1])
                            damage += 100;
                    if(gp.player.hit_point <= 0) {
                        gp.player.hit_point = 0;
                        gp.panelState = gp.END;
                        gp.es.setTitleTexts("You Died :(\nNumber of steps: " + gp.player.steps);
                    }
                    else battleState = 1;
                    rolling = false;
                    diceCounter = 0;
                }
                else if(battleState == 3) {
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
                        gp.panelState = gp.END;
                        gp.es.setTitleTexts("You Died :(\nNumber of steps: " + gp.player.steps);
                    }
                    else if(gp.monstersM.monsters[monsterIndex].hit_point <= 0) {
                        gp.monstersM.monsters[monsterIndex].hit_point = 0;
                        battleState = 4;
                    }
                    rolling = false;
                    diceCounter = 0;
                }
                else if(battleState == 4) {
                    int heal = (gp.player.dice[0]+1)*10 + gp.player.dice[1]+1;
                    gp.player.hit_point += heal;
                    endBattle();
                }
            }
            else {
                if(battleState == 0) {
                    if(gp.keyH.rPressed) {
                        rolling = true;
                    }
                }
                else if(battleState == 1 || battleState == 3) {
                    if(gp.keyH.rPressed) {
                        rolling = true;
                    }
                }
                else if(battleState == 2) {
                   rolling = true;
                }
                else if(battleState == 4) {
                    if(gp.monstersM.monsters[monsterIndex].symbol == Entity.ZORK) {
                        if(endGameCounter >= 56) {
                            endGameCounter = 0;
                            endBattle();
                        }
                        endGameCounter++;
                    }
                    else if(gp.keyH.rPressed) {
                        rolling = true;
                    }
                }
            }
        }
    }
}