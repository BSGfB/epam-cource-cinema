package com.epam.cinema.dao;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.model.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile("database")
@Loggable
public class JdbcAuditoriumDao implements AuditoriumDao {

    private static final String NUMBER_OF_SEATS = "number_of_seats";
    private static final String NAME = "name";
    private static final String AUDITORIUM_ID = "auditorium_id";

    @Value("${query.auditorium.save}")
    private String save;

    @Value("${query.auditorium.remove}")
    private String remove;

    @Value("${query.auditorium.getById}")
    private String getById;

    @Value("${query.auditorium.getAll}")
    private String getAll;

    @Value("${query.auditorium.getByName}")
    private String getByName;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAuditoriumDao (DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Auditorium getByName(String name) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(NAME, name);
        return jdbcTemplate.queryForObject(getByName, parameterSource, new AuditoriumRowMapper());
    }

    @Override
    public Long save(Auditorium object) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(NAME, object.getName())
                .addValue(NUMBER_OF_SEATS, object.getNumberOfSeats());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(save, mapSqlParameterSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void remove(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(AUDITORIUM_ID, id);
        jdbcTemplate.update(remove, parameterSource);
    }

    @Override
    public Auditorium getById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(AUDITORIUM_ID, id);
        return jdbcTemplate.queryForObject(getById, parameterSource, new AuditoriumRowMapper());
    }

    @Override
    public List<Auditorium> getAll() {
        return jdbcTemplate.query(getAll, new AuditoriumRowMapper());
    }

    public static final class AuditoriumRowMapper implements RowMapper<Auditorium> {
        @Override
        public Auditorium mapRow(ResultSet resultSet, int i) throws SQLException {
            Auditorium auditorium = new Auditorium();
            auditorium.setId(resultSet.getLong(AUDITORIUM_ID));
            auditorium.setName(resultSet.getString(NAME));
            auditorium.setNumberOfSeats(resultSet.getInt(NUMBER_OF_SEATS));

            return auditorium;
        }
    }
}
