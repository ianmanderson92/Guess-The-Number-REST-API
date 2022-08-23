package com.sg.guessnum.service;

import com.sg.guessnum.controller.GuessNumController;
import com.sg.guessnum.dao.GuessNumDao;
import com.sg.guessnum.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class GuessNumServiceLayerImpl implements GuessNumServiceLayer
{
    GuessNumDao dao;

    @Autowired
    public GuessNumServiceLayerImpl( GuessNumDao dao )
    {
        this.dao = dao;
    }

    @Override
    public List<Game> getAllGames()
    {
        List<Game> resultGamesList = dao.getAllGames();

        for ( Game gameIter : resultGamesList )
        {
            if ( !gameIter.isFinished() )
            {
                gameIter.setUnits( 0 );
                gameIter.setTens( 0 );
                gameIter.setHundreds( 0 );
                gameIter.setThousands( 0 );
                gameIter.setAnswer( 0 );
            }
        }
        return resultGamesList;
    }

    @Override
    public Game getGameById( int id )
    {
        Game foundGame = dao.findGameById( id );
        if ( !foundGame.isFinished() )
        {
            foundGame.setUnits( 0 );
            foundGame.setTens( 0 );
            foundGame.setHundreds( 0 );
            foundGame.setThousands( 0 );
            foundGame.setAnswer( 0 );
        }
        return foundGame;
    }

    @Override
    public Game startNewGame()
    {
        int[] unitsArray = generateAnswerUnits();
        Game newGame = new Game( unitsArray[0], unitsArray[1], unitsArray[2], unitsArray[3] );
        newGame.setAnswer();
        dao.addGame( newGame );
        return newGame;
    }

    public int[] generateAnswerUnits()
    {
        int[] unitsArray = {-1,-1,-1,-1}; //array that holds individual digits and answer.
        Random rand = new Random();
        unitsArray[0] = rand.nextInt(10);
        for (int i=1; i<4; i++)
        {
            do
            {
                int tempNum = rand.nextInt(10);
                if ( IntStream.of( unitsArray ).noneMatch( x -> x == tempNum ) )
                {
                    unitsArray[i] = tempNum;
                }
            } while ( unitsArray[i] == -1 );
        }
        return unitsArray;
    }
}
