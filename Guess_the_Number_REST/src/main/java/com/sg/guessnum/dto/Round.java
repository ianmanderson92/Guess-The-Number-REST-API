package com.sg.guessnum.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Round
{
    private int roundId;
    private int gameId;
    private int userGuess;
    private String roundResultsString;
    private LocalDateTime timestamp;

    public Round()
    {
    }

    public Round( int gameId, int userGuess, int exactMatches, int partialMatches )
    {
        this.gameId = gameId;
        this.userGuess = userGuess;
        this.roundResultsString = "e:" + exactMatches + ":p:" + partialMatches;
        this.timestamp = LocalDateTime.now();
    }

    public void setGameId( int gameId )
    {
        this.gameId = gameId;
    }

    public int getRoundId()
    {
        return roundId;
    }

    public int getGameId()
    {
        return gameId;
    }

    public int getUserGuess()
    {
        return userGuess;
    }

    public String getRoundResultsString()
    {
        return roundResultsString;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setRoundId( int roundId )
    {
        this.roundId = roundId;
    }

    public void setUserGuess( int userGuess )
    {
        this.userGuess = userGuess;
    }

    public void setRoundResultsString( String roundResultsString )
    {
        this.roundResultsString = roundResultsString;
    }

    public void setTimestamp( LocalDateTime timestamp )
    {
        this.timestamp = timestamp;
    }

    public void setTimestamp( Timestamp timestamp )
    {
        this.timestamp = timestamp.toLocalDateTime();
    }
}
