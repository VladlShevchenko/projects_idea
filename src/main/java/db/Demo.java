package db;

import db.entity.User;

import java.util.List;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        System.out.println(UserDao.findUser((long) 1));
        System.out.println(UserDao.findUserByLogin("Oleg"));
        System.out.println(PublicationDao.findPublications());
        User user = new User();
        user.setLogin("Ira");
        user.setEmail("irOchka");
        user.setPassword("1234");
        user.setBill(20000);
        user.setRoleId(1);
        UserDao.insertUser(user);
        System.out.println(UserDao.findAllUsers());

    }

}
