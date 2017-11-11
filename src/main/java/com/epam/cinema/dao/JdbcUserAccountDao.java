package com.epam.cinema.dao;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.model.UserAccount;
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
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile("database")
@Loggable
public class JdbcUserAccountDao implements UserAccountDao {

    private static final String USER_ACCOUNT_ID = "user_account_id";
    private static final String USER_ID = "user_id";
    private static final String MONEY = "money";

    @Value("${query.userAccount.save}")
    private String save;

    @Value("${query.userAccount.remove}")
    private String remove;

    @Value("${query.userAccount.getById}")
    private String getById;

    @Value("${query.userAccount.getAll}")
    private String getAll;

    @Value("${query.userAccount.addMoney}")
    private String addMoney;

    @Value("${query.userAccount.removeMoney}")
    private String removeMoney;

    @Value("${query.userAccount.getByUserId}")
    private String getByUserId;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserAccountDao (DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void addMoney(Long userId, BigDecimal bigDecimal) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID, userId)
                .addValue(MONEY, bigDecimal);

        jdbcTemplate.update(addMoney, mapSqlParameterSource);
    }

    @Override
    public int removeMoney(Long userId, BigDecimal bigDecimal) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID, userId)
                .addValue(MONEY, bigDecimal);

        return jdbcTemplate.update(removeMoney, mapSqlParameterSource);
    }

    @Override
    public UserAccount getByUserId(Long userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(USER_ID, userId);
        return jdbcTemplate.queryForObject(getByUserId, parameterSource, new UserAccountRowMapper());
    }

    @Override
    public Long save(UserAccount object) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ACCOUNT_ID, object.getId())
                .addValue(USER_ID, object.getUserId())
                .addValue(MONEY, object.getMoney());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(save, mapSqlParameterSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void remove(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(USER_ACCOUNT_ID, id);
        jdbcTemplate.update(remove, parameterSource);
    }

    @Override
    public UserAccount getById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(USER_ACCOUNT_ID, id);
        return jdbcTemplate.queryForObject(getById, parameterSource, new UserAccountRowMapper());
    }

    @Override
    public List<UserAccount> getAll() {
        return jdbcTemplate.query(getAll, new UserAccountRowMapper());
    }

    private static final class UserAccountRowMapper implements RowMapper<UserAccount> {
        @Override
        public UserAccount mapRow(ResultSet resultSet, int i) throws SQLException {
            UserAccount userAccount = new UserAccount();
            userAccount.setId(resultSet.getLong(USER_ACCOUNT_ID));
            userAccount.setUserId(resultSet.getLong(USER_ID));
            userAccount.setMoney(resultSet.getBigDecimal(MONEY));

            return userAccount;
        }
    }
}
