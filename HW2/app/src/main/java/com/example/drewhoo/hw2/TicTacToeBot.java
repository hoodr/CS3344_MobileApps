package com.example.drewhoo.hw2;

import android.util.Log;
import java.util.Random;

/**
 * Created by drewhoo on 2/8/17.
 */

public class TicTacToeBot {
    private static final String TAG = "TicTacToeBot";
    private int strategy;
    private Game game;

    public TicTacToeBot(){}

    public void setObserver(Game game){
        this.game = game;
    }


    public int[] getMove(int difficulty){
        int[] move;
        switch (difficulty){
            case 1:
                move = easy();
                Log.d(TAG, "Get easy move ");
                break;
            case 2:
                move = med();
                Log.d(TAG, "Get Med move ");
                break;
            case 3:
                move =hard();
                Log.d(TAG, "Get Hard move");
                break;
            default:
                move = new int[] { 3, 3};
                Log.e(TAG, "No move");
                break;
        }
        Log.d(TAG, "Cmp Move: " + move[0] + " " + move[1]);
        return move;
    }

    private int[] hard(){
        return new int[] {3, 3};

    }

    private int[] med(){
        return new int[] {3, 3};
    }

    private int[] easy(){
        Random rand = new Random();
        int x = rand.nextInt(3);
        int y = rand.nextInt(3);
        return new int[] {x, y};
    }

//    private int[] checkTwoInaRow(){
//
//    }

}

/**
 *
 * When X plays corner first (best move for them), and O is not a perfect player, the following may happen:
 * If O responds with a center mark (best move for them), a perfect X player will take the corner opposite the original.
 * Then O should play an edge.
 * However, if O plays a corner as its second move, a perfect X player will mark the remaining corner, blocking O's 3-in-a-row and making their own fork.
 * If O responds with a corner mark, X is guaranteed to win, by simply taking any of the other two corners and then the last, a fork.
    * (since when X takes the third corner, O can only take the position between the two X's.
    * Then X can take the only remaining corner to win)
 * If O responds with an edge mark, X is guaranteed to win, by taking center, then O can only take the corner opposite the corner which X plays first.
 * Then X can take a corner to win.
 *
 *
 *
 *
 *
 * Win: If the player has two in a row, they can place a third to get three in a row.
 * Block: If the opponent has two in a row, the player must play the third themselves to block the opponent.
 * Fork: Create an opportunity where the player has two threats to win (two non-blocked lines of 2).
 * Blocking an opponent's fork:
    * Option 1: The player should create two in a row to force the opponent into defending, as long as it doesn't result in them creating a fork. For example, if "X" has a corner, "O" has the center, and "X" has the opposite corner as well, "O" must not play a corner in order to win. (Playing a corner in this scenario creates a fork for "X" to win.)
    * Option 2: If there is a configuration where the opponent can fork, the player should block that fork.
 * Center: A player marks the center. (If it is the first move of the game, playing on a corner gives "O" more opportunities to make a mistake and may therefore be the better choice; however, it makes no difference between perfect players.)
 * Opposite corner: If the opponent is in the corner, the player plays the opposite corner.
 * Empty corner: The player plays in a corner square.
 * Empty side: The player plays in a middle square on any of the 4 sides.
 */