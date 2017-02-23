package com.example.drewhoo.gameswithlistview;

/**
 * Created by drewhoo on 2/21/17.
 */

import android.util.Log;

import java.util.Random;

public class HangmanGame {
    private static final String TAG = "Game ";
    private String word;
    private int attempts;
    private String output;
    private String guess;
    private boolean stopGameFlag = false;

    public HangmanGame(){
        word = generateWord();
        attempts = 0;
        initialOutput(word);
    }

    public String display(){
        return output;
    }

    private void initialOutput(String word){
        StringBuilder initial = new StringBuilder();
        for (int i = 0; i < word.length(); i++){
            initial.append('-');
        }
        guess = initial.toString();
        output = initial.toString() + "  consists of " + word.length() + " characters";
    }

    public boolean guessLetter(char letter){
        boolean flag = false;
        attempts += 1;

        for (int index = word.indexOf(letter); index >= 0; index = word.indexOf(letter, index + 1)) {
            Log.d(TAG, letter + " " + index);
            handleCorrect(index, letter);
            flag = true;
        }

        if (flag) {
            checkWin();
            return true;
        } else {
            handleIncorrect();
            return false;
        }
    }

    private void handleCorrect(int index, char letter){
        char[] guessChars = guess.toCharArray();
        guessChars[index] = letter;
        guess = String.valueOf(guessChars);
        Log.d(TAG, "handleCorrect: " + guess);
    }

    private void handleIncorrect(){
        if (attempts >= 13){
            handleLose();
        } else {
            output = guess + "   you used " + attempts + " out of 13 guesses";
        }
    }

    private void checkWin(){
        Log.d(TAG, "Guess: " + guess + " Word: " + word);
        if (guess.equals(word)){
            Log.d(TAG, "User wins");
            output = "you WIN, the word was: " + word + " you used " + attempts + " guesses";
            stopGame();
        } else {
            output = guess + "   you used " + attempts + " out of 13 guesses";
        }
    }

    private void stopGame(){
        stopGameFlag = !stopGameFlag;
    }

    public boolean getGameStatus(){
        return stopGameFlag;
    }

    private void handleLose(){
        output = "you LOST, the word was: " + word + " you used " + attempts + " guesses";
    }

    public String generateWord(){
        String [] words = {"handler","against","horizon","chops","junkyard","amoeba","academy","roast",
                "countryside","children","strange","best","drumbeat","amnesiac","chant","amphibian","smuggler","fetish"};
        Random r = new Random();
        int index = r.nextInt(words.length);
        Log.d(TAG, words[index]);
        return words[index];
    }
}