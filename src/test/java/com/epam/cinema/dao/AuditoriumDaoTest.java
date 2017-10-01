package com.epam.cinema.dao;

import com.epam.cinema.model.Auditorium;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/app-spring-context.xml"})
public class AuditoriumDaoTest {

    private static final String AUDITORIUM_NAME = "Big";

    @Autowired
    private AuditoriumDao auditoriumDao;

    @Test
    public void getByName() throws Exception {
        Auditorium auditorium = auditoriumDao.getByName(AUDITORIUM_NAME);

        Assert.assertEquals(AUDITORIUM_NAME, auditorium.getName());
    }

}