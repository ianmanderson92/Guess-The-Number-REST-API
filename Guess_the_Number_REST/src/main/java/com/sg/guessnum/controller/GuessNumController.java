package com.sg.guessnum.controller;

import com.sg.guessnum.dao.GuessNumDao;
import com.sg.guessnum.dto.Game;
import com.sg.guessnum.service.GuessNumServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Game startNewGame()
    {
        return service.startNewGame();
    }

    @GetMapping( "/game" )
    public List<Game> getAllGames()
    {
        return service.getAllGames();
    }


    public void run()
    {

    }
}
