package com.epam.cinema.dao;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
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

@Component
@Profile("database")
public class JdbcTicketDao implements TicketDao {

    private static final String EVENT_ID = "event_id";
    private static final String USER_ID = "user_id";
    private static final String TICKET_ID = "ticket_id";
    private static final String SEAT = "seat";
    private static final String DATE_TIME = "date_time";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${query.ticket.save}")
    private String save;

    @Value("${query.ticket.remove}")
    private String remove;

    @Value("${query.ticket.getById}")
    private String getById;

    @Value("${query.ticket.getAll}")
    private String getAll;

    @Value("${query.ticket.getPurchasedTicketsForEvent}")
    private String getPurchasedTicketsForEvent;

    @Autowired
    public JdbcTicketDao (DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Ticket> getPurchasedTicketsForEvent(final Event event, final LocalDateTime dateTime) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(EVENT_ID, event.getId())
                .addValue(USER_ID, dateTime);

        return jdbcTemplate.query(getAll, mapSqlParameterSource, new TicketRowMapper());
    }

    @Override
    public Long save(final Ticket object) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(EVENT_ID, object.getEvent().getId())
                .addValue(USER_ID, object.getUser().getId())
                .addValue(SEAT, object.getSeat())
                .addValue(DATE_TIME, object.getDateTime());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(save, mapSqlParameterSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void remove(final Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(EVENT_ID, id);
        jdbcTemplate.update(remove, parameterSource);
    }

    @Override
    public Ticket getById(final Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(USER_ID, id);
        return jdbcTemplate.queryForObject(getById, parameterSource, new TicketRowMapper());
    }

    @Override
    public List<Ticket> getAll() {
        return jdbcTemplate.query(getAll, new TicketRowMapper());
    }

    private static final class TicketRowMapper implements RowMapper<Ticket> {
        @Override
        public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
            Event event = new Event();
            event.setId(resultSet.getLong(EVENT_ID));

            User user = new User();
            user.setId(resultSet.getLong(USER_ID));

            Ticket ticket = new Ticket();
            ticket.setId(resultSet.getLong(TICKET_ID));
            ticket.setSeat(resultSet.getInt(SEAT));
            ticket.setEvent(event);
            ticket.setUser(user);
            ticket.setDateTime(resultSet.getTimestamp(DATE_TIME).toLocalDateTime());

            return ticket;
        }
    }
}
