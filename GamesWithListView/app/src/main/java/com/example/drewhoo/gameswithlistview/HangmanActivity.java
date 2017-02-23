package com.example.drewhoo.gameswithlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HangmanActivity extends AppCompatActivity {
    private static final String TAG = "HangmanActivity ";
    HangmanGame currentGame;
    TextView wordOut;
    ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);
        startGame();
    }

    public void startGame(){
        wordOut = (TextView) findViewById(R.id.wordOut);
        currentGame = new HangmanGame();
        currentGame.generateWord();
        wordOut.setText(currentGame.display());
    }

    public void onClick(View keyboard){
        Button b = (Button) keyboard;
        String inputText = b.getText().toString();
        Log.d(TAG, inputText);

        if (inputText.equals("Reset") ){
            currentGame = new HangmanGame();
            resetButtonVis();
            wordOut.setText(currentGame.display());
        } else if (currentGame.getGameStatus()){
            doNothing();
        } else {
            b.setVisibility(View.INVISIBLE);
            buttons.add(b);

            currentGame.guessLetter(inputText.toLowerCase().toCharArray()[0]);
            wordOut.setText(currentGame.display());
        }
    }

    private void doNothing(){
        String schmett = "dwe";
        Log.d(TAG, schmett);
    }

    private void resetButtonVis(){
        for (Button b : buttons){
            b.setVisibility(View.VISIBLE);
        }
    }
}

