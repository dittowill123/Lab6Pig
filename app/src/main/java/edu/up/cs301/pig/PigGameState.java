package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
   private int playerID;
   private int player0;
   private int player1;
   private int runningTotal;
   private int valueDie;

    public PigGameState () {
        playerID = 0;
        player0 = 0;
        player1 = 0;
        runningTotal = 0;
        valueDie = 1;

    }

    public PigGameState (PigGameState pig) {
        this.playerID = pig.playerID;
        this.player0 = pig.player0;
        this.player1 = pig.player1;
        this.runningTotal = pig.runningTotal;
        this.valueDie = pig.valueDie;

    }

    public int getPlayerID() {
        return playerID;
    }
    public int getPlayer0() {
        return player0;
    }
    public int getPlayer1() {
        return player1;
    }
    public int getRunningTotal() {
        return runningTotal;
    }
    public int getValueDie() {
        return valueDie;
    }

    public void setPlayerID(int pID) {
        this.playerID = pID;
    }
    public void setPlayer0(int p0) {
        this.player0 = p0;
    }
    public void setPlayer1(int p1) {
        this.player1 = p1;
    }
    public void setRunningTotal(int rT) {
        this.runningTotal = rT;
    }
    public void setValueDie(int vD) {
        this.valueDie = vD;
    }
}

