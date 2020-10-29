package daotest;


import db.PublicationDao;
import db.entity.Publication;
import db.entity.Topic;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class PublicationDaoTest {
    @Test
    public void testPublicationDao() {
        Publication publication = PublicationDao.findPublicationById(1);
        List<Publication> pub4;
        List<Publication> pub1 = PublicationDao.findPublications();
        Publication pub = PublicationDao.findPublicationByName("Хазяїн");
        List<Publication> pub2 = PublicationDao.findPublicationsForPagination(1, 2);
        List<Publication> pub3 = PublicationDao.findPublicationForAccount(1);
        try {
            pub4 = PublicationDao.findPublicationForCart(1);
            Assert.assertEquals(pub4.get(0).getId(), 1);
        }catch (IndexOutOfBoundsException ex){

        }
        List<Topic> topics = PublicationDao.findTopic();

        Assert.assertEquals(publication.getName(), "Хазяїн");
        Assert.assertEquals(pub1.get(2).getName(), "Пізнайко");
        Assert.assertEquals(pub.getId(), 1);
        Assert.assertEquals(pub2.get(1).getName(), "ЗОЖ");
        Assert.assertEquals(pub3.get(0).getId(), 1);
        Assert.assertEquals(topics.size(), 4);

    }
}
