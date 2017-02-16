package com.example.drewhoo.hw2;

import android.util.Log;

/**
 * Created by drewhoo on 2/8/17.
 */

public class Game {
    private static final String TAG = "Game";
    private int difficulty;
    private int playerWins;
    private int compWins;
    private int[][] playerBoard;
    private int[][] cmpBoard;
    private int draws;
    private int turn;
    private Views view;
    private TicTacToeBot ticTacToeBot;

    public Game(Views view, TicTacToeBot ticTacToeBot){
        newBoard();
        this.view = view;
        this.view.setObserver(this);
        this.ticTacToeBot = ticTacToeBot;
        this.ticTacToeBot.setObserver(this);
        turn = 0;
        playerWins = 0; compWins = 0; draws = 0;
        difficulty = 1; // TEMP
    }

    private void newBoard(){
        cmpBoard = new int[3][3];
        playerBoard = new int[3][3];
    }


    public void setDifficulty(String inputDiff){
        Log.d(TAG, inputDiff);

        switch (inputDiff){
            case "Easy":
                this.difficulty = 1;
                Log.d(TAG, "Difficulty level " + difficulty);
                break;
            case "Med":
                this.difficulty = 2;
                Log.d(TAG, "Difficulty level " + difficulty);
                break;
            case "Hard":
                this.difficulty = 3;
                Log.d(TAG, "Difficulty level " + difficulty);
                break;
            default:
                Log.e(TAG, inputDiff + " is not a valid difficulty level");
                break;
        }

    }

    public void newGame(){
        Log.d(TAG, " new game");
        newBoard();
        view.resetViews();
    }

    private boolean isLegal(int[] coord){
        Log.d(TAG, "in isLegal cmpBoard[" + coord[0] + "][" + coord[1] + "] = " + cmpBoard[coord[0]][coord[1]]);
        Log.d(TAG, "in isLegal playerBoard[" + coord[0] + "][" + coord[1] + "] = " + playerBoard[coord[0]][coord[1]]);

        return playerBoard[coord[0]][coord[1]] == 0 && cmpBoard[coord[0]][coord[1]] == 0;
    }

//    private boolean isTie(int[] coord){
//        board[coord[0]][coord[1]] = 1;
//        int total = 0;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                total += board[i][j];
////                Log.d(TAG, "board[" + i + "][" + j + "] = " + board[i][j]);
//            }
//        }
//        if (total == 9){
//            return true;
//        } else {
//            board[coord[0]][coord[1]] = 0;
//            return false;
//        }
//    }


    public void playerMove(int[] coord){
        Log.d(TAG, " player move " + coord[0] + " " + coord[1]);

        if (isLegal(coord)){
            updateBoard(coord, 0);
            view.updateBoard(coord, 0);

            if (hasWonUser(coord)){
                win(0);
            } else if (isTie()) {
                Log.d(TAG, " DRAW or someone fucked up");
                tie();
            } else {
                turn++;
                view.updateBoard(coord, 0);
                computerMove();
            }
        } else {
            Log.d(TAG, " Illegal move");
        }
    }

    public void ready(){
//        computerMove();
    }

    private boolean computerMove(){
        Log.d(TAG, " Computer move");

        int[] move = ticTacToeBot.getMove(difficulty);

        if (isLegal(move)){
            view.updateBoard(move, 1);
            updateBoard(move, 1);

            if (hasWonCmp(move)){
                Log.d(TAG, " CMP won");
                win(1);
                return true;
            } else if (isTie()) {
//            call new game
                Log.d(TAG, " DRAW or someone fucked up");
                tie();
                return true;
            } else {
                turn++;
                return true;
            }
        } else {
            Log.e(TAG, "Illegal move");
            return computerMove();
        }

    }

    private void updateBoard(int[] coord, int player){
        int x = coord[0];
        int y = coord[1];
        if(player == 0){
            Log.d(TAG, "updateBoard Player");
            playerBoard[x][y] = 1;
        } else {
            Log.d(TAG, "updateBoard Comp");
            cmpBoard[x][y] = 1;
        }
    }

    private boolean isTie(){
        int total = 0;
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard[i].length; j++) {
                total += playerBoard[i][j];
                total += cmpBoard[i][j];
            }
        }
        return total == 9;
    }

    private boolean hasWonUser(int[] coord){
        int x = coord[0];
        int y = coord[1];
        return (playerBoard[x][0] == 1 && playerBoard[x][1] == 1 && playerBoard[x][2] == 1 // 3-in-the-row
                ||
                playerBoard[0][y] == 1 && playerBoard[1][y] == 1 && playerBoard[2][y] == 1 // 3-in-the-column
                ||
                x == y && playerBoard[0][0] == 1 && playerBoard[1][1] == 1 && playerBoard[2][2] == 1 // 3-in-the-diagonal
                ||
                x + y == 2 && playerBoard[0][2] == 1 && playerBoard[1][1] == 1 && playerBoard[2][0] == 1); // 3-in-the-opposite-diagonal
    }

    private boolean hasWonCmp(int[] coord){
        int x = coord[0];
        int y = coord[1];
        return (cmpBoard[x][0] == 1 && cmpBoard[x][1] == 1 && cmpBoard[x][2] == 1 // 3-in-the-row
                ||
                cmpBoard[0][y] == 1 && cmpBoard[1][y] == 1 && cmpBoard[2][y] == 1 // 3-in-the-column
                ||
                x == y && cmpBoard[0][0] == 1 && cmpBoard[1][1] == 1 && cmpBoard[2][2] == 1 // 3-in-the-diagonal
                ||
                x + y == 2 && cmpBoard[0][2] == 1 && cmpBoard[1][1] == 1 && cmpBoard[2][0] == 1); // 3-in-the-opposite-diagonal
    }

    private void tie(){
//        call update scoreboard for tie
        draws++;
        setScoreBoard(2);
    }

    private void win(int winner){
//      Change scoreboard
//      Call New Game
        switch (winner){
            case 0:
                playerWins++;
                Log.d(TAG, " PLAYER WINS");
                setScoreBoard(0);
//                Call scoreboard adjuster
                break;
            case 1:
                compWins++;
                Log.d(TAG, " COMPUTER WINS");
                setScoreBoard(1);
//                call scoreboard adjuster
                break;
            default:
                Log.e(TAG, "Fail no one wins");
                break;
        }
    }

    private void setScoreBoard(int winner){
        Log.d(TAG, "int winner = " + winner);
        view.setScoreboard(playerWins, compWins, draws, winner);
    }

}
