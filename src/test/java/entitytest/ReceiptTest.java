package entitytest;
import db.entity.Receipt;
import db.entity.User;
import org.junit.*;

import static org.junit.Assert.*;
public class ReceiptTest {
    @Test
    public void testUserEntity() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt);

        receipt.setId(10);
        receipt.setUserId(1);
        receipt.setStatusId(2);

        assertEquals(receipt.getId(), 10);
        assertEquals(receipt.getUserId(), 1);
        assertEquals(receipt.getStatusId(), 2);
        assertEquals(receipt.toString(),"Order [userId=1, statusId=2, getId()=10]");

    }
}
