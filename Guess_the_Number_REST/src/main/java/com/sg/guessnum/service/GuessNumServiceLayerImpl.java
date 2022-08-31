package com.sg.guessnum.service;

import com.sg.guessnum.controller.GuessNumController;
import com.sg.guessnum.dao.GuessNumDao;
import com.sg.guessnum.dto.Game;
import com.sg.guessnum.dto.Guess;
import com.sg.guessnum.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    /**
     special method that retrieves Game object but leaves answer data intact for unfinished games.
     * @param id
     * @return
     */
    private Game getGameByIdForGuess( int id )
    {
        return dao.findGameById( id );
    }

    @Override
    public Round makeGuess( Guess userGuess )
    {
        Game roundGame = getGameByIdForGuess( userGuess.getGameId() );
        int[] userGuessUnitsArray = unmarshallGuessNum( userGuess.getGuessNum() );

        //Compare guess with game answer and calculate results. Store results in Round object.

        //@roundResultsArray [0]: exactResults, [1]: partialResults.
        int[] roundResultsArray = calculateGuessResults( roundGame, userGuessUnitsArray );

        if ( roundResultsArray[0] == 4 )
        {
            dao.finishGame( roundGame );
        }

        Round currentRound = new Round( userGuess.getGameId(), userGuess.getGuessNum(),
            roundResultsArray[ 0 ], roundResultsArray[ 1 ] );

        dao.addRound( currentRound );
        return currentRound;
    }

    private int[] unmarshallGuessNum( int guessNum )
    {
        int[] userGuessUnitsArray = new int[4];

        userGuessUnitsArray[0] = guessNum/1000;
        guessNum -= ( userGuessUnitsArray[0] * 1000);
        userGuessUnitsArray[1] = guessNum/100;
        guessNum -= ( userGuessUnitsArray[1] * 100);
        userGuessUnitsArray[2] = guessNum/10;
        guessNum -= ( userGuessUnitsArray[2] * 10);
        userGuessUnitsArray[3] = guessNum;

        return userGuessUnitsArray;
    }
    
    private int[] calculateGuessResults( Game roundGame, int[] userGuessUnitsArray)
    {
        int exactMatches = 0;
        int partialMatches = 0;
        
        if( roundGame.getThousands() == userGuessUnitsArray[0])
        {
            exactMatches++;
        }
        else if ( ArrayUtils.contains( userGuessUnitsArray, roundGame.getThousands() ))
        {
            partialMatches++;
        }

        if( roundGame.getHundreds() == userGuessUnitsArray[1])
        {
            exactMatches++;
        }
        else if ( ArrayUtils.contains( userGuessUnitsArray, roundGame.getHundreds() ))
        {
            partialMatches++;
        }

        if( roundGame.getTens() == userGuessUnitsArray[2])
        {
            exactMatches++;
        }
        else if ( ArrayUtils.contains( userGuessUnitsArray, roundGame.getTens() ))
        {
            partialMatches++;
        }

        if( roundGame.getUnits() == userGuessUnitsArray[3])
        {
            exactMatches++;
        }
        else if ( ArrayUtils.contains( userGuessUnitsArray, roundGame.getUnits() ))
        {
            partialMatches++;
        }

        int[] roundResultsArray = new int[2];
        roundResultsArray[0] = exactMatches;
        roundResultsArray[1] = partialMatches;

        return roundResultsArray;
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
        int[] unitsArray = {-1,-1,-1,-1}; //array that holds individual digits.
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

    @Override
    public List<Round> getAllRoundsByGame( int id )
    {
        return dao.findRoundsByGameId( id );
    }
}
