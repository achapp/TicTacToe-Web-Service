package com.achappell.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class TicTacToeGame
{
    private final int BOARD_SIZE = 3;
    private final char[] PLAYERS = {'X', 'O'};

    private char[][] board;
    private int currentPlayer;
    private String winner;
    private boolean isOver;
    private int moves;

    @JsonProperty
    public String getTurn() { return "Player " + PLAYERS[currentPlayer]; }

    @JsonProperty
    public String getWinner()
    {
        return winner;
    }

    @JsonProperty
    public char[][] getBoard() { return board; }

    public TicTacToeGame()
    {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        boardInit();
        isOver = false;
        moves = 0;
        winner = "None";
        currentPlayer = 0;
    }

    private void boardInit()
    {
        for(char[] row : board)
        {
            Arrays.fill(row, '-');
        }
    }

    public synchronized void play(char player, int row, int col)
    {
        if(isOver)
        {
            throw new IllegalMoveException("The game is already over. No more moves can be made.");
        }

        if(String.valueOf(player).equalsIgnoreCase(String.valueOf(PLAYERS[currentPlayer]))) {
            place(row, col);
            moves++;

            if (checkWinner()) {
                winner = "Player " + PLAYERS[currentPlayer];
                isOver = true;
            } else if (moves >= BOARD_SIZE * BOARD_SIZE) {
                winner = "Tie";
                isOver = true;
            } else {
                changePlayer();
            }
        }
        else
        {
            throw new IllegalMoveException("It is not this player's turn.");
        }
    }

    private void place(int row, int col)
    {
        if(row > BOARD_SIZE - 1 || row < 0 || col > BOARD_SIZE - 1 || col < 0)
        {
            throw new IllegalMoveException(String.format("Space %d,%d is not on this board", row, col ));
        }

        if(board[row][col] != '-')
        {
            throw new IllegalMoveException(String.format("Space %d,%d is already occupied", row, col ));
        }

        board[row][col] = PLAYERS[currentPlayer];
    }

    private void changePlayer()
    {
        currentPlayer = (currentPlayer + 1) % 2;
    }

    private boolean checkWinner()
    {
        return checkRows(PLAYERS[currentPlayer]) || checkColumns(PLAYERS[currentPlayer]) || checkDiagonals(PLAYERS[currentPlayer]);
    }

    private boolean checkRows(char player)
    {
        for(char[] row:board)
        {
            boolean hasWonSoFar = true;
            for(int c = 0; c<row.length && hasWonSoFar; c++)
            {
                hasWonSoFar &= player == row[c];
            }

            if(hasWonSoFar){
                return true;
            }
        }

        return false;
    }

    private boolean checkColumns(char player)
    {
        for(int c = 0; c<board[0].length; c++)
        {
            boolean hasWonSoFar = true;
            for(int r = 0; r<board.length && hasWonSoFar; r++)
            {
                hasWonSoFar &= player == board[r][c];
            }

            if(hasWonSoFar){
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonals(char player)
    {
        boolean hasWonSoFar;

        //top-left to bottom-right
        hasWonSoFar = true;
        for(int i = 0; i<board.length && hasWonSoFar; i++)
        {
            hasWonSoFar &= player == board[i][i];
        }
        if(hasWonSoFar){
            return true;
        }

        //bottom-left to top-right
        hasWonSoFar = true;
        for(int i = 0; i<board.length && hasWonSoFar; i++)
        {
            hasWonSoFar &= player == board[i][board.length - 1 - i];
        }
        if(hasWonSoFar){
            return true;
        }

        return false;
    }
}