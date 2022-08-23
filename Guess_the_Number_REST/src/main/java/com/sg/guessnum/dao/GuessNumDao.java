package com.sg.guessnum.dao;

import com.sg.guessnum.dto.Game;

import java.util.List;

public interface GuessNumDao
{

    public Game addGame( Game game );

    public Game findGameById( int id );

    public List<Game> getAllGames();

}
