package db;

import db.entity.Publication;
import db.entity.Receipt;
import db.entity.User;

import java.util.Collections;
import java.util.List;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
       /* System.out.println(UserDao.findUser((long) 1));
        System.out.println(UserDao.findUserByLogin("Oleg"));
        System.out.println(PublicationDao.findPublications());
        List<Publication> publication= Collections.singletonList(PublicationDao.findPublicationByName("Хаз"));
        String i="1";
        int a= Integer.parseInt(i);
        System.out.println(publication);*/

        /*Receipt receipt = new ReceiptDao().findReceiptForCart(2,1);
        System.out.println(receipt);
        new ReceiptDao().deleteReceipt(receipt.getId());*/
     //   List<Publication> publications=new PublicationDao().findPublicationForAccount(3);
       // List<Publication> users_publications=new PublicationDao().findPublicationForCart(3);
       // System.out.println(publications);
       /* List<Receipt> receipts = new ReceiptDao().findReceiptForBuy(3);
        System.out.println(receipts);
        for (Receipt item : receipts) {
            item.setStatusId(1);
            ReceiptDao.updateReceipt(item);
        }
        List<Receipt> receiptsAft = new ReceiptDao().findReceiptForBuy(3);
        System.out.println(receiptsAft);*/
String role="ADMIN";
boolean b=role.equals(Constant.ROLE_ADMIN);
        System.out.println(b);
       /* User user = new User();
        user.setLogin("Ira");
        user.setEmail("irOchka");
        user.setPassword("1234");
        user.setBill(20000);
        user.setRoleId(1);
        UserDao.insertUser(user);*/
      //  System.out.println(UserDao.findAllUsers());

    }

}
