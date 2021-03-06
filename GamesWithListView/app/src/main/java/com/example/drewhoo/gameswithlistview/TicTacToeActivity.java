package com.example.drewhoo.gameswithlistview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class TicTacToeActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity ";
    private Views view;
    private TextView gameStatus;
    boolean firstClick;
    private TextView scoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        Views view = new Views(this);
        this.view = view;
        TicTacToeBot ticTacToeBot = new TicTacToeBot();
        TicTacToeGame game = new TicTacToeGame(view, ticTacToeBot);
        firstClick = false;
        scoreBoard = (TextView) findViewById(R.id.score);

        view.setBoard(createButtonArray());
    }

    public int[][] createButtonArray(){
        int[][] board= new int[3][3];
        ArrayList<Integer> buttons = new ArrayList<>(Arrays.asList( R.id.btn0,
                R.id.btn1,
                R.id.btn2,
                R.id.btn3,
                R.id.btn4,
                R.id.btn5,
                R.id.btn6,
                R.id.btn7,
                R.id.btn8));
        int k = 0;
        Log.d(TAG, "board.length " + board.length);
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                Button b = (Button) findViewById(buttons.get(k));
                board[i][j] = b.getId();
                k++;
//                Log.d(TAG, "ID of button " + b.getId());
            }
        }
        return board;
    }

    public void onClick(View keyboard){
        if (!firstClick){
            firstClick = true;
            gameStatus = (TextView) findViewById(R.id.gameText);
            gameStatus.setText("");
        }
        view.onClick(keyboard);
    }

    public void update(int button, int player){
        Log.d(TAG, " Update Board " + button);
        Button b = (Button) findViewById(button);
//        Log.d(TAG, "Button " + b);

        switch (player){
            case 0:
                Log.d(TAG, " set X");
                b.setText("X");
                b.setTextColor(Color.rgb(200, 0, 0));
                break;
            case 1:
                Log.d(TAG, " set O");
                b.setText("O");
                b.setTextColor(Color.rgb(0, 200, 0));
                break;
            default:
                Log.e(TAG, " ERROR");
                break;
        }
    }

//    public void gameSetting(View keyboard){
//        Button b = (Button) keyboard;
//        String inputText = b.getText().toString();
//        Log.d(TAG, inputText);
//
//        view.setDifficulty(inputText);
//
//        LinearLayout landing_page = (LinearLayout) this.findViewById(R.id.landingpage);
//        LinearLayout content_main = (LinearLayout) this.findViewById(R.id.content_main);
//
//        landing_page.setVisibility(LinearLayout.GONE);
//
//        content_main.setVisibility(LinearLayout.VISIBLE);
//    }

    public void setScoreBoard(int playerWins, int computerWins, int draws, int winner){
        String s = "Wins: " + playerWins + " Loses: " + computerWins + " Ties: " + draws;
        Log.d(TAG, s);

        switch (winner){
            case 0:
                gameStatus.setText(getResources().getString(R.string.game_over) + " " + getResources().getString(R.string.player_win));
                break;
            case 1:
                gameStatus.setText(getResources().getString(R.string.game_over) + " " + getResources().getString(R.string.cmp_win));
                break;
            case 2:
                gameStatus.setText(getResources().getString(R.string.game_over) + " " + getResources().getString(R.string.draw));
                break;
            default:
                Log.e(TAG, "error with scoreboard");
                break;
        }
        scoreBoard.setText(s);
    }

    public void resetBoard(int button){
        Button b = (Button) findViewById(button);
        b.setText("");
    }


}