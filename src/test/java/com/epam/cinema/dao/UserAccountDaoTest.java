package com.epam.cinema.dao;

import com.epam.cinema.configuration.SpringTestConfiguration;
import com.epam.cinema.model.UserAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringTestConfiguration.class)
@Transactional
public class UserAccountDaoTest {

    private static final BigDecimal EXTRA_MONEY = new BigDecimal(100.0);
    private static final long ID = 1L;

    @Autowired
    private UserAccountDao userAccountDao;

    @Test
    public void addMoney() throws Exception {
        UserAccount userAccount = userAccountDao.getById(1L);
        userAccountDao.addMoney(userAccount.getUserId(), EXTRA_MONEY);

        Assert.assertTrue(EXTRA_MONEY.compareTo(userAccountDao.getById(ID).getMoney().subtract(userAccount.getMoney())) == 0);
    }

    @Test
    public void removeMoney() throws Exception {
        UserAccount userAccount = userAccountDao.getById(ID);

        userAccountDao.addMoney(userAccount.getUserId(), EXTRA_MONEY);
        userAccountDao.removeMoney(userAccount.getUserId(), EXTRA_MONEY);

        Assert.assertTrue(userAccount.getMoney().compareTo(userAccountDao.getById(ID).getMoney()) == 0);
    }

    @Test
    public void removeMoneyTooMuchMoney() throws Exception {
        UserAccount userAccount = userAccountDao.getById(ID);

        userAccountDao.addMoney(userAccount.getUserId(), EXTRA_MONEY);
        Integer result = userAccountDao.removeMoney(userAccount.getUserId(), userAccount.getMoney().add(EXTRA_MONEY));

        Assert.assertTrue(result == 0);
    }
}