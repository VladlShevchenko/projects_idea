package db;

import java.util.List;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        System.out.println(UserDao.findUser((long) 1));
        System.out.println(UserDao.findUserByLogin("Oleg"));

    }

}
