package com.example.drewhoo.gameswithlistview;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by drewhoo on 2/10/17.
 */

public class Views extends AppCompatActivity {
    private static final String TAG = "Views";
    private TicTacToeGame game;
    private int[][] board;

    private TicTacToeActivity mainActivity;

    public Views(TicTacToeActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void setObserver(TicTacToeGame game){
        this.game = game;
    }

    private int[] lookUpTable(Button b){
        for (int i = 0 ; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == b.getId()) {
                    Log.d(TAG, "index of button " + i + " " + j);
                    return new int[]{i, j};
                }
            }
        }
        Log.d(TAG, "Button not found");
        return new int[] {};
    }

    public void onClick(View keyboard){
        Button b = (Button) keyboard;
        Log.d(TAG, " Button " + b.getId());
        int[] coord = lookUpTable(b);

        String inputText = b.getText().toString();
        Log.d(TAG, inputText);

        if (inputText.equals("Again?") ){
            game.newGame();
        } else {
            game.playerMove(coord);
        }
    }

    public void updateBoard(int[] coord, int player){
        int button = board[coord[0]][coord[1]];
        Log.d(TAG, " Update Board " + button + " Player " + player);

        mainActivity.update(button, player);

    }

    private void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Log.d(TAG, ""+ board[i][j]);
            }
        }
    }

    public void setBoard(int[][] b){
        board = b;
    }

    public void setDifficulty(String inputText){
        game.setDifficulty(inputText);
    }

    public void setScoreboard(int playerWins, int computerWins, int draws, int winner){
        mainActivity.setScoreBoard(playerWins, computerWins, draws, winner);
    }

    public void resetViews(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                mainActivity.resetBoard(board[i][j]);
            }
        }
    }
}