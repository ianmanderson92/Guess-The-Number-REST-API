package com.sg.guessnum.service;

import com.sg.guessnum.dto.Game;
import com.sg.guessnum.dto.Guess;
import com.sg.guessnum.dto.Round;

import java.util.List;

public interface GuessNumServiceLayer
{
    public List<Game> getAllGames();

    public Game getGameById( int id );

    public Round makeGuess( Guess userGuess );

    public Game startNewGame();
}
