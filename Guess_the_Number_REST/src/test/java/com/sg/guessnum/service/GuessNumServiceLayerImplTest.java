package com.sg.guessnum.service;

import com.sg.guessnum.dto.Game;
import com.sg.guessnum.dto.Guess;
import com.sg.guessnum.dto.Round;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@Sql("classpath:test_db_init_script.sql") //causes the sql script to run before every test so the testDb is consistent.
class GuessNumServiceLayerImplTest
{
    @Autowired
    GuessNumServiceLayer service;

    @Test
    void getAllGames()
    {
        List<Game> foundGames = service.getAllGames();
        assertEquals( 6, foundGames.size(), "Did not return all games from DB." );
    }

    @Test
    void getGameById()
    {
        Game foundGame = service.getGameById( 4 );
        assertTrue( foundGame != null, "Game not found in DB." );
        assertEquals( 4, foundGame.getGameId(), "Wrong game returned from DB." );
    }

    @Test
    void makeGuess()
    {
        Guess newGuess = new Guess( 3, 5286 );
        service.makeGuess( newGuess );
        List<Round> foundRounds = service.getAllRoundsByGame( 3 );
        assertEquals( 1, foundRounds.size(), "Guess not handled correctly." );
    }

    @Test
    void startNewGame()
    {
        Game newGame = service.startNewGame();
        List<Game> foundGames = service.getAllGames();
        assertTrue( newGame != null, "New game created correctly." );
        assertEquals( 7, foundGames.size(), "New game not added to DB correctly." );
    }

    @Test
    void getAllRoundsByGame()
    {
        List<Round> foundRounds = service.getAllRoundsByGame( 5 );
        assertEquals( 2, foundRounds.size(), "Rounds not returned from DB." );
    }
}