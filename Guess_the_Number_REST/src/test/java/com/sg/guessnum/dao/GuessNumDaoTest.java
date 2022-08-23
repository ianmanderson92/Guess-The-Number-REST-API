package com.sg.guessnum.dao;

import com.sg.guessnum.dto.Game;
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
class GuessNumDaoTest
{
    @Autowired
    GuessNumDao dao;

    @Test
    void addGame()
    {
        Game newGame = new Game(1, 2, 3, 4);
        newGame = dao.addGame( newGame );

        Game foundGame = dao.findGameById( newGame.getGameId() );
        assertEquals( newGame.getGameId(), foundGame.getGameId(), "Game not added to DB" );
    }

    @Test
    void addRound()
    {
        Round newRound = new Round(6, 3861, 4, 0);
        newRound = dao.addRound( newRound );

        List<Round> foundRounds = dao.findRoundsByGameId( newRound.getGameId() );
        boolean isRoundInResults = false;
        for ( Round iterRound : foundRounds )
        {
            if ( iterRound.getRoundId() == newRound.getRoundId() )
            {
                isRoundInResults = true;
            }
        }
        assertTrue( isRoundInResults, "Round not added to DB" );
    }

    @Test
    void finishGame()
    {
        Game foundGame = dao.findGameById( 4 );
        dao.finishGame( foundGame );
        foundGame = dao.findGameById( 4 );
        assertTrue( foundGame.isFinished(), "Game not set to finished in DB" );
    }

    @Test
    void findGameById()
    {
        Game foundGame = dao.findGameById( 4 );
        assertTrue( foundGame != null, "Game not found in DB." );
        assertEquals( 4, foundGame.getGameId(), "Wrong game found in DB." );
    }

    @Test
    void findRoundsByGameId()
    {
        List<Round> foundRounds = dao.findRoundsByGameId( 5 );
        assertEquals( 2, foundRounds.size(), "Did not return all rounds from DB." );
    }

    @Test
    void getAllGames()
    {
        List<Game> foundGames = dao.getAllGames();
        assertEquals( 6, foundGames.size(), "Did not return all games from DB." );
    }
}