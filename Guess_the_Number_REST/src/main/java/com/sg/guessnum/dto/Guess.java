package com.sg.guessnum.dto;

public class Guess
{
    private int gameId;

    private int guessNum;

    public Guess( int gameId, int guessNum )
    {
        this.gameId = gameId;
        this.guessNum = guessNum;
    }

    public int getGameId()
    {
        return gameId;
    }

    public void setGameId( int gameId )
    {
        this.gameId = gameId;
    }

    public int getGuessNum()
    {
        return guessNum;
    }

    public void setGuessNum( int guessNum )
    {
        this.guessNum = guessNum;
    }
}
