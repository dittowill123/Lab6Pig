package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigSmartComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        if (!(info instanceof PigGameState)){
            return;
        }

        int lead = 0; //int to calculate difference in points
        PigGameState pig = (PigGameState)info;
        if (pig.getPlayerID() == 0){
            if ((pig.getPlayer0() + pig.getRunningTotal()) >= 50){ //if game is won
                game.sendAction(new PigHoldAction(this));
            }
            if (pig.getPlayer0() < 35 && pig.getPlayer1() >= 35 && pig.getRunningTotal() < 13){
                game.sendAction(new PigRollAction(this));
            }
            if (pig.getPlayer0() >= 35 && (pig.getPlayer0() + pig.getRunningTotal()) - pig.getPlayer1() <= 8) { //if lead is over 10
                game.sendAction(new PigRollAction(this));
            }
            if (pig.getRunningTotal() < 10){
                game.sendAction(new PigRollAction(this));
            }
            game.sendAction(new PigHoldAction(this));
        }
        if (pig.getPlayerID() == 1){
            if ((pig.getPlayer1() + pig.getRunningTotal()) >= 50){ //if game is won
                game.sendAction(new PigHoldAction(this));
            }
            if (pig.getPlayer1() < 35 && pig.getPlayer0() >= 35 && pig.getRunningTotal() < 13){
                game.sendAction(new PigRollAction(this));
            }
            if (pig.getPlayer1() >= 35 && (pig.getPlayer1() + pig.getRunningTotal()) - pig.getPlayer0() <= 8) { //if lead is over 10
                game.sendAction(new PigRollAction(this));
            }
            if (pig.getRunningTotal() < 10){ 
                game.sendAction(new PigRollAction(this));
            }
            game.sendAction(new PigHoldAction(this));
        }

    }//receiveInfo

}

