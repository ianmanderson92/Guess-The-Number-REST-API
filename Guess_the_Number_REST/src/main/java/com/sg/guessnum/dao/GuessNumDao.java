package com.sg.guessnum.dao;

import com.sg.guessnum.dto.Game;
import com.sg.guessnum.dto.Round;

import java.util.List;

public interface GuessNumDao
{

    public Game addGame( Game game );

    public Round addRound( Round round );

    public boolean finishGame( Game game );

    public Game findGameById( int id );

    public List<Round> findRoundsByGameId( int id );

    public List<Game> getAllGames();

}
