package com.epam.cinema.dao;

import com.epam.cinema.model.CounterPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
@Qualifier("JdbcBookingTicketCounterDao")
@Profile("database")
public class JdbcBookingTicketCounterDao implements CounterDao {

    private static final String NAME = "name";
    private static final String COUNTER = "counter";

    @Value("${query.JdbcBookingTicketCounter.save}")
    private String save;

    @Value("${query.JdbcBookingTicketCounter.increaseCounter}")
    private String increaseCounter;

    @Value("${query.JdbcBookingTicketCounter.getByName}")
    private String getByName;

    @Value("${query.JdbcBookingTicketCounter.getAll}")
    private String getAll;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBookingTicketCounterDao (DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long save(String name) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(NAME, name);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(save, mapSqlParameterSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void increaseCounter(String name) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(NAME, name);

        jdbcTemplate.update(increaseCounter, mapSqlParameterSource);
    }

    @Override
    public CounterPair getByName(String name) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(NAME, name);
        return jdbcTemplate.queryForObject(getByName, parameterSource,
                (resultSet, i) -> new CounterPair(resultSet.getString(NAME), resultSet.getInt(COUNTER)));
    }

    @Override
    public List<CounterPair> getAll() {
        return jdbcTemplate.query(getAll,
                (resultSet, i) -> new CounterPair(resultSet.getString(NAME), resultSet.getInt(COUNTER)));
    }
}
