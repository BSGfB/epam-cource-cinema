package com.epam.cinema.dao;

import com.epam.cinema.dto.UserDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
@Profile("database")
public class JdbcDiscountDao implements DiscountDao {

    private static final String USER_ID = "user_id";

    private static final String DISCOUNT_ID = "discount_id";

    private static final String DISCOUNT_NAME = "discount_name";

    private static final String PRICE = "price";
    public static final String EMAIL = "email";

    @Value("${query.discount.save}")
    private String save;

    @Value("${query.discount.getAll}")
    private String getAll;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcDiscountDao (DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long save(UserDiscount userDiscount) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID, userDiscount.getUserId())
                .addValue(DISCOUNT_NAME, userDiscount.getDiscountName())
                .addValue(PRICE, userDiscount.getPrice());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(save, mapSqlParameterSource, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public List<UserDiscount> getAll() {
        return jdbcTemplate.query(getAll,
                (resultSet, i) -> {
                    UserDiscount userDiscount = new UserDiscount();
                    userDiscount.setDiscountId(resultSet.getLong(DISCOUNT_ID));
                    userDiscount.setDiscountName(resultSet.getString(DISCOUNT_NAME));
                    userDiscount.setUserId(resultSet.getLong(USER_ID));
                    userDiscount.setPrice(resultSet.getDouble(PRICE));
                    userDiscount.setEmail(resultSet.getString(EMAIL));

                    return userDiscount;
                });
    }
}
