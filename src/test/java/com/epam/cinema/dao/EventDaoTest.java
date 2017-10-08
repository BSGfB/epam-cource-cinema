package com.epam.cinema.dao;

import com.epam.cinema.configuration.SpringTestConfiguration;
import com.epam.cinema.model.Event;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringTestConfiguration.class)
public class EventDaoTest {

    public static final String EVENT_NAME = "Kingsman 2";

    @Autowired
    private EventDao eventDao;

    @Test
    public void getByName() throws Exception {
        Event event = eventDao.getByName(EVENT_NAME);

        Assert.assertEquals(EVENT_NAME, event.getName());
    }

}