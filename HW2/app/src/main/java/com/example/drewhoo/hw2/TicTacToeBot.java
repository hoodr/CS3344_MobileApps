package com.example.drewhoo.hw2;

import android.util.Log;
import java.util.Random;

/**
 * Created by drewhoo on 2/8/17.
 */

public class TicTacToeBot {
    private static final String TAG = "TicTacToeBot";
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

//    Temp (will add logic later)
    private int[] hard(){
        return new int[] {3, 3};

    }

//    Temp (will add logic later)
    private int[] med(){
        return new int[] {3, 3};
    }

    private int[] easy(){
        Random rand = new Random();
        int x = rand.nextInt(3);
        int y = rand.nextInt(3);
        return new int[] {x, y};
    }
}