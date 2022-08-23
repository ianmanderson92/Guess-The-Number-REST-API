package com.sg.guessnum.controller;

import com.sg.guessnum.dto.Game;
import com.sg.guessnum.dto.Guess;
import com.sg.guessnum.dto.Round;
import com.sg.guessnum.service.GuessNumServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/guessnum")
public class GuessNumController
{
    //TODO: access service layer only

    private final GuessNumServiceLayer service;

    public GuessNumController( GuessNumServiceLayer service )
    {
        this.service = service;
    }

    @PostMapping( "/begin" )
    public ResponseEntity<String> startNewGame()
    {
        Game newGame = service.startNewGame();
        if ( newGame == null )
        {
            return new ResponseEntity( null, HttpStatus.EXPECTATION_FAILED );
        }
        return new ResponseEntity<String>( "Game created with ID: " + String.valueOf( newGame.getGameId() )
            , HttpStatus.CREATED );
    }

    @PostMapping( "/guess" )
    public Round makeGuess( @RequestBody Guess userGuess )
    {
        return service.makeGuess( userGuess );
    }


    @GetMapping( "/game" )
    public List<Game> getAllGames()
    {
        return service.getAllGames();
    }

    @GetMapping( "/game/{gameId}" )
    public ResponseEntity<Game> getGameById( @PathVariable int gameId )
    {
        Game foundGame = service.getGameById( gameId );
        if ( foundGame == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }
        return ResponseEntity.ok( foundGame );
    }

    //@GetMapping( "/rounds/{gameId}" )

}
