package entitytest;

import db.entity.Publication;
import org.junit.*;

import static org.junit.Assert.*;
public class PublicationTest {
    @Test
    public void testPublicationEntity() {
        Publication publication = new Publication();

        assertNotNull(publication);

        publication.setId(10);
        publication.setName("Пізнайко");
        publication.setPriceForMonth(15);
        publication.setImage("/images/mainer.png");
        publication.setDescription("Some description");
        publication.setTopicId(2l);

        assertEquals(publication.getId(), 10);
        assertEquals(publication.getName(), "Пізнайко");
        assertEquals(publication.getPriceForMonth(), 15,0);
        assertEquals(publication.getImage(), "/images/mainer.png");
        assertEquals(publication.getDescription(), "Some description");
        assertEquals(publication.getTopicId(), 2,0);
        assertEquals(publication.toString(),"Publication [name=Пізнайко, price for month=15.0, imageBlob=/images/mainer.png, description=Some description, topicId=2, getId()=10];");


    }
}
