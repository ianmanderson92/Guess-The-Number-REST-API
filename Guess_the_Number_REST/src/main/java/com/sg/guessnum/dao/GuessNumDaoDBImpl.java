package com.sg.guessnum.dao;

import com.sg.guessnum.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    //TODO: finish implementing and setting up getAllGames().
    @Override
    public List<Game> getAllGames()
    {
        final String sqlQuery = "SELECT id, thousands, hundreds, tens, units, finished FROM game;";
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
            gameObj.setFinished( resultSet.getBoolean( "finished" ) );
            return gameObj;
        }
    }

    //TODO: create Romapper class for Guess.
}
