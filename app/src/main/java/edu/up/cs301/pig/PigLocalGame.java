package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {
    private PigGameState pigState;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        super();
        pigState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        return playerIdx == pigState.getPlayerID(); //check if player id matches player who can move
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method
        if (action instanceof PigHoldAction) {
           if (pigState.getPlayerID() == 1) {
               pigState.setPlayer1(pigState.getPlayer1() + pigState.getRunningTotal());
               pigState.setRunningTotal(0);
           }
           else {
               pigState.setPlayer0(pigState.getPlayer0() + pigState.getRunningTotal());
               pigState.setRunningTotal(0);
           }
           //set to other player's turn
            if (pigState.getPlayerID() == 1) {
                pigState.setPlayerID(0);
            } else {
                pigState.setPlayerID(1);
            }
            return true; //if pig hold action is legal
        } else if (action instanceof PigRollAction) {
            //set die value to random int 1-6
            int random = 1 + (int) (Math.random() * (5) + 1); //generate random value
            pigState.setValueDie(random); //set die to random value

            if (pigState.getValueDie() != 1) {
                pigState.setRunningTotal(pigState.getRunningTotal() + pigState.getValueDie());
            } else if (pigState.getValueDie() == 1) {
                pigState.setRunningTotal(0);
                if (pigState.getPlayerID() == 1) {
                    pigState.setPlayerID(0);
                } else {
                    pigState.setPlayerID(1);
                }
            }
            return true; //if pig roll action is legal
        }

        return false;

    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        p.sendInfo(new PigGameState(pigState));
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if (pigState.getPlayer0() >= 50){
            return "Player 1 has won the game with " + pigState.getPlayer0() + " points";
        }
        else if (pigState.getPlayer1() >= 50){
            return "Player 2 has won the game with " + pigState.getPlayer1() + " points";
        }
        return null;
    }

}// class PigLocalGame
