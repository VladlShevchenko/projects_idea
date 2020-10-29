package entitytest;

import db.entity.User;
import org.junit.*;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testUserEntity() {
        User user = new User();

        assertNotNull(user);

        user.setId(10);
        user.setLogin("Vlad");
        user.setEmail("grgrey25@gmail.com");
        user.setBill(10000);
        user.setRoleId(1);

        assertEquals(user.getId(), 10);
        assertEquals(user.getLogin(), "Vlad");
        assertEquals(user.getEmail(), "grgrey25@gmail.com");
        assertEquals(user.getBill(), 10000,0);
        assertEquals(user.getRoleId(), 1);
        assertEquals(user.toString(),"User [login=Vlad,bill=10000, email= grgrey25@gmail.com, 1 =roleId ]");
    }
}
