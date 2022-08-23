package com.sg.guessnum.service;

import com.sg.guessnum.dto.Game;

import java.util.List;

public interface GuessNumServiceLayer
{
    public List<Game> getAllGames();

    public Game getGameById( int id );

    public Game startNewGame();
}
