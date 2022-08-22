package com.sg.guessnum.dao;

import com.sg.guessnum.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.List;

@Component
@Profile( "database" )
public class GuessNumDaoDBImpl implements GuessNumDao
{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessNumDaoDBImpl ( JdbcTemplate jdbcTemplate )
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game addGame( Game game )
    {
        final String sql = "INSERT INTO game(units, tens, hundreds, thousands, answer, finished)" +
                            " VALUES(?,?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( ( Connection connection ) ->
        {
            PreparedStatement prepStatement = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS );

            prepStatement.setInt( 1, game.getUnits() );
            prepStatement.setInt( 2, game.getTens() );
            prepStatement.setInt( 3, game.getHundreds() );
            prepStatement.setInt( 4, game.getThousands() );
            prepStatement.setInt( 5, game.getAnswer() );
            prepStatement.setBoolean( 6, game.isFinished() );
            return prepStatement;
        }, keyHolder );

        game.setGameId( keyHolder.getKey().intValue() );
        return game;
    }

    @Override
    public List<Game> getAllGames()
    {
        final String sqlQuery = "SELECT id, units, tens, hundreds, thousands, answer, finished FROM game;";
        return jdbcTemplate.query( sqlQuery, new GameMapper() );
    }

    private static final class GameMapper implements RowMapper<Game>
    {
        @Override
        public Game mapRow( ResultSet resultSet, int index ) throws SQLException
        {
            Game gameObj = new Game();
            gameObj.setGameId( resultSet.getInt( "id" ) );
            gameObj.setThousands( resultSet.getInt( "thousands" ) );
            gameObj.setHundreds( resultSet.getInt( "hundreds" ) );
            gameObj.setTens( resultSet.getInt( "tens" ) );
            gameObj.setUnits( resultSet.getInt( "units" ) );
            gameObj.setAnswer( resultSet.getInt( "answer" ));
            gameObj.setFinished( resultSet.getBoolean( "finished" ) );
            return gameObj;
        }
    }

    //TODO: create RowMapper class for Guess.
}
