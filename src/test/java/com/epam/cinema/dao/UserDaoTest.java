package com.epam.cinema.dao;


import com.epam.cinema.configuration.SpringTestConfiguration;
import com.epam.cinema.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.isNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringTestConfiguration.class)
@Transactional
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private static final User USER = new User();

    @Before
    public void init() {
        USER.setFirstName("Alison");
        USER.setLastName("Brie");
        USER.setBirthday(LocalDate.of(1982, 12, 29));
        USER.setEmail("Alison_Brie@gmail.com");
    }

    @After
    public void after() {
        if (isNull(USER.getId()))
            userDao.remove(USER.getId());
    }

    @Test
    public void save() throws Exception {
        Long id = userDao.save(USER);
        USER.setId(id);

        User user = userDao.getById(id);

        Assert.assertEquals(USER.getFirstName(), user.getFirstName());
        Assert.assertEquals(USER.getLastName(), user.getLastName());
        Assert.assertEquals(USER.getEmail(), user.getEmail());
    }

    @Test
    public void remove() throws Exception {
        final Long id = userDao.save(USER);
        USER.setId(id);

        userDao.remove(id);
        Assert.assertTrue(userDao.getAll().stream().noneMatch(user -> Objects.equals(user.getId(), id)));
    }

    @Test
    public void getById() throws Exception {
        Long id = userDao.save(USER);
        USER.setId(id);

        User user = userDao.getById(id);

        Assert.assertEquals(USER, user);
    }

    @Test
    public void getByEmail() throws Exception {
        Long id = userDao.save(USER);
        USER.setId(id);

        User user = userDao.getById(id);

        Assert.assertEquals(USER.getEmail(), user.getEmail());
    }

    @Test
    public void getAll() throws Exception {
        Long id = userDao.save(USER);
        USER.setId(id);

        Assert.assertTrue(userDao.getAll().size() > 0);
    }

}
