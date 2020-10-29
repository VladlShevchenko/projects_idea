package daotest;


import db.UserDao;
import db.entity.Topic;
import db.entity.User;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    @Test
    public void testUserDao() {

        User userById = UserDao.findUserById(1l);
        User userByLogin=UserDao.findUserByLogin("Vova");
        List<User> accounts = UserDao.findAllUsers();
        List<User> accounts1 = UserDao.findOnlyUsers();
        User user=new User();
        user.setLogin("Vlad");
        user.setEmail("grgrey25@gmail.com");
        user.setBill(10000);
        user.setRoleId(1);
        UserDao.insertUser(user);
        user.setEmail("grgrey");
        UserDao.updateUser(user);

        Assert.assertEquals(userById.getLogin(), "Oleg");
        Assert.assertEquals(userByLogin.getId(), 2);
        Assert.assertEquals(accounts.get(0).getLogin(), "Oleg");
        Assert.assertEquals(accounts1.get(0).getLogin(), "Shmek");
        Assert.assertEquals(user.getLogin(), "Vlad");
        Assert.assertEquals(user.getEmail(), "grgrey");

    }
}
