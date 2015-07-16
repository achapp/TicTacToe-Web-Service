package com.achappell.core;
;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class TicTacToeGameTest {
    TicTacToeGame game;

    @Rule
    public ExpectedException thrown= ExpectedException.none();


    @Test(expected = IllegalMoveException.class)
    public void illegalMoveAfterGameOver()
    {
        initGame1();
        game.play('X',1,1);
    }

    @Test(expected = IllegalMoveException.class)
    public void illegalMoveSpotTaken()
    {
        game = new TicTacToeGame();
        game.play('X',1, 1);
        game.play('X',1,1);
    }

    @Test(expected = IllegalMoveException.class)
    public void illegalMoveBadCoordsNeg()
    {
        game = new TicTacToeGame();
        game.play('X',-1,-1);
    }

    @Test(expected = IllegalMoveException.class)
    public void illegalMoveBadCoordsOver()
    {
        game = new TicTacToeGame();
        game.play('X',1,4);
    }

    @Test(expected = IllegalMoveException.class)
    public void illegalMoveNotYourTurn()
    {
        game = new TicTacToeGame();
        game.play('O',1,1);
    }

    @Test
    public void turnsAlternate()
    {
        game = new TicTacToeGame();
        assertEquals(game.getTurn(), "Player X");
        game.play('X',1,1);
        assertEquals(game.getTurn(), "Player O");
        game.play('O',1,2);
        assertEquals(game.getTurn(), "Player X");
    }

    @Test
    public void rowWin()
    {
        initGame3();
        game.play('X',1,2);
        assertEquals(game.getWinner(), "Player X");
    }

    @Test
    public void colWin()
    {
        initGame2();
        game.play('X',1,1);

        assertEquals(game.getWinner(), "Player X");
    }

    @Test
    public void diagonalWin()
    {
        initGame3();
        game.play('X',0,2);
        assertEquals(game.getWinner(), "Player X");
    }

    @Test
    public void tie()
    {
        initGame1();
        assertEquals(game.getWinner(), "Tie");
    }

    public void initGame1()
    {
        game = new TicTacToeGame();

        // XOX
        // XOX
        // OXO
        game.play('X',0,0);
        game.play('O',0,1);
        game.play('X',0,2);
        game.play('O',1,1);
        game.play('X',1,0);
        game.play('O',2,0);
        game.play('X',1,2);
        game.play('O',2,2);
        game.play('X',2,1);
    }

    public void initGame2()
    {
        game = new TicTacToeGame();

        // -X-
        // O-O
        // -X-
        game.play('X',0,1);
        game.play('O',1,0);
        game.play('X',2,1);
        game.play('O',1,2);
    }

    public void initGame3()
    {
        game = new TicTacToeGame();

        // OO-
        // XX-
        // XO-
        game.play('X',1,0);
        game.play('O',0,0);
        game.play('X',1,1);
        game.play('O',0,1);
        game.play('X',2,0);
        game.play('O',2,1);
    }
}