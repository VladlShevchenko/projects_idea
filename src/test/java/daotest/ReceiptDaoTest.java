package daotest;

import db.ReceiptDao;
import db.UserDao;
import db.entity.Receipt;
import db.entity.Topic;
import db.entity.User;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class ReceiptDaoTest {
    @Test
    public void testReceiptDao() {

        List<Receipt> receipts = ReceiptDao.findReceipts();
        Receipt receipt1 = ReceiptDao.findReceiptForCart(1,1);
        List<Receipt> receipt2= ReceiptDao.findReceiptForBuy(1);
        Receipt receipt=new Receipt();
        receipt.setUserId(1);
        receipt.setStatusId(1);
        ReceiptDao.insertReceipt(receipt);
        receipt.setUserId(2);
        ReceiptDao.updateReceipt(receipt);

        Assert.assertEquals(receipts.get(0).getId(), 1);
        Assert.assertEquals(receipt1.getUserId(), 1);
        Assert.assertEquals(receipt2.get(0).getUserId(), 1);
        Assert.assertEquals(receipt.getStatusId(), 1);
        Assert.assertEquals(receipt.getUserId(), 2);

    }
}
