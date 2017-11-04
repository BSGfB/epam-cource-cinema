package com.epam.cinema.dao;

import com.epam.cinema.model.Role;
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
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@Profile("database")
public class JdbcUserDao implements UserDao {

    private static final String USER_ID = "user_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String BIRTHDAY = "birthday";
    private static final String MESSAGE_TEXT = "message_text";
    private static final String MESSAGES = "messages";
    private static final String ROLE_NAME = "role_name";
    private static final String ROLE_ID = "role_id";
    private static final String PASSWORD = "password";

    @Value("${query.user.save}")
    private String save;

    @Value("${query.user.remove}")
    private String remove;

    @Value("${query.user.getById}")
    private String getById;

    @Value("${query.user.getAll}")
    private String getAll;

    @Value("${query.user.getByEmail}")
    private String getByEmail;

    @Value("${query.user.addMessage}")
    private String addMessage;

    @Value("${query.user.getRoleId}")
    private String getRoleId;

    @Value("${query.user.setRole}")
    private String setRole;

    @Value("${query.user.getRoles}")
    private String getRoles;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDao (DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User getByEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(EMAIL, email);
        return jdbcTemplate.queryForObject(getByEmail, parameterSource, new UserRowMapper());
    }

    @Override
    public void addMessage(Long id, String messageText) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID, id)
                .addValue(MESSAGE_TEXT, messageText);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(addMessage, mapSqlParameterSource, keyHolder);
    }

    @Override
    public Long getRoleId(Role role) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(ROLE_NAME, role.toString().toUpperCase());
        return jdbcTemplate.queryForObject(getRoleId, parameterSource, (resultSet, i) -> resultSet.getLong(ROLE_ID));
    }

    @Override
    public List<Role> getRoles(Long userId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID, userId);
        return jdbcTemplate.query(getRoles, mapSqlParameterSource, new RoleRowMapper());
    }

    @Override
    public void setRole(Long user_id, Long role_id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID, user_id)
                .addValue(ROLE_ID, role_id);

        jdbcTemplate.update(setRole, mapSqlParameterSource);
    }

    @Override
    public Long save(User object) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(FIRST_NAME, object.getFirstName())
                .addValue(LAST_NAME, object.getLastName())
                .addValue(EMAIL, object.getEmail())
                .addValue(BIRTHDAY, object.getBirthday())
                .addValue(PASSWORD, object.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(save, mapSqlParameterSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void remove(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(USER_ID, id);
        jdbcTemplate.update(remove, parameterSource);
    }

    @Override
    public User getById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(USER_ID, id);
        return jdbcTemplate.queryForObject(getById, parameterSource, new UserRowMapper());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(getAll, new UserRowMapper());
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong(USER_ID));
            user.setFirstName(resultSet.getString(FIRST_NAME));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setBirthday(resultSet.getDate(BIRTHDAY).toLocalDate());

            try {
                user.setPassword(resultSet.getString(PASSWORD));
            } catch (SQLException e) {}

            String messages = resultSet.getString(MESSAGES);
            if (!isNull(messages))
                user.setMessages(Arrays.asList(messages.split("@#")));

            return user;
        }
    }

    private static final class RoleRowMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            return Role.get(resultSet.getString(ROLE_NAME));
        }
    }

}
