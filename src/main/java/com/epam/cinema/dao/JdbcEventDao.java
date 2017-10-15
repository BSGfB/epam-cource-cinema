package com.epam.cinema.dao;

import com.epam.cinema.dto.AuditoriumWithDateTime;
import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.EventRating;
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
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Component
@Profile("database")
public class JdbcEventDao implements EventDao {

    private static final String EVENT_ID = "event_id";
    private static final String NAME = "name";
    private static final String BASE_PRICE = "base_price";
    private static final String RATING = "rating";
    private static final String EVENT_RATING_ID = "event_rating_id";
    public static final String USER_ID = "user_id";
    public static final String AUDITORIUM_ID = "auditorium_id";
    public static final String START_TIME = "start_time";

    @Value("${query.event.save}")
    private String save;

    @Value("${query.event.remove}")
    private String remove;

    @Value("${query.event.getById}")
    private String getById;

    @Value("${query.event.getAll}")
    private String getAll;

    @Value("${query.event.getByName}")
    private String getByName;

    @Value("${query.event.getAuditoriumsByEventId}")
    private String getAuditoriumsByEventId;

    @Value("${query.event.getEventRatingId}")
    private String getEventRatingId;

    @Value("${query.event.addAuditorium}")
    private String addAuditorium;


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcEventDao (DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public Event getByName(String name) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(NAME, name);
        Event event = jdbcTemplate.queryForObject(getByName, parameterSource, new EventRowMapper());

        return addAuditoriumsToEvent(event);
    }

    @Override
    public Event addAuditorium(Long id, LocalDateTime localDateTime, Auditorium auditorium) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(EVENT_ID, id)
                .addValue(AUDITORIUM_ID, auditorium.getId())
                .addValue(START_TIME, localDateTime);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(addAuditorium, mapSqlParameterSource, keyHolder);

        return getById(id);
    }

    @Override
    public Long save(Event object) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(NAME, object.getName())
                .addValue(BASE_PRICE, object.getBasePrice())
                .addValue(EVENT_RATING_ID, getEventRatingId(object.getRating()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(save, mapSqlParameterSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void remove(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(EVENT_ID, id);
        jdbcTemplate.update(remove, parameterSource);
    }

    @Override
    public Event getById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(EVENT_ID, id);
        Event event = jdbcTemplate.queryForObject(getById, parameterSource, new EventRowMapper());

        return addAuditoriumsToEvent(event);
    }

    @Override
    public List<Event> getAll() {
        return jdbcTemplate.query(getAll, new EventRowMapper()).stream().map(this::addAuditoriumsToEvent).collect(toList());
    }

    private List<AuditoriumWithDateTime> auditoriumWithDateTimes(final Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(EVENT_ID, id);
        return jdbcTemplate.query(getAuditoriumsByEventId, parameterSource, new AuditoriumWithDateTimeRowMapper());
    }

    private Event addAuditoriumsToEvent(final Event event) {
        auditoriumWithDateTimes(event.getId()).forEach(auditoriumWithDateTime -> {
            event.getAuditoriums().put(auditoriumWithDateTime.getStartTime(), auditoriumWithDateTime.getAuditorium());
        });

        return event;
    }

    private Long getEventRatingId(EventRating eventRating) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(NAME, eventRating.toString().toUpperCase());
        return jdbcTemplate.queryForObject(getEventRatingId, parameterSource, (resultSet, i) -> resultSet.getLong(EVENT_RATING_ID));
    }

    private static final class EventRowMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet resultSet, int i) throws SQLException {
            Event event = new Event();
            event.setId(resultSet.getLong(EVENT_ID));
            event.setName(resultSet.getString(NAME));
            event.setBasePrice(resultSet.getDouble(BASE_PRICE));
            event.setRating(EventRating.get(resultSet.getString(RATING)));

            return event;
        }
    }

    private static final class AuditoriumWithDateTimeRowMapper implements RowMapper<AuditoriumWithDateTime> {
        @Override
        public AuditoriumWithDateTime mapRow(ResultSet resultSet, int i) throws SQLException {
            AuditoriumWithDateTime auditoriumWithDateTime = new AuditoriumWithDateTime();
            auditoriumWithDateTime.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
            auditoriumWithDateTime.setAuditorium(new JdbcAuditoriumDao.AuditoriumRowMapper().mapRow(resultSet, i));

            return auditoriumWithDateTime;
        }
    }
}
