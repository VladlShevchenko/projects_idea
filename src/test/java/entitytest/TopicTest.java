package entitytest;

import db.entity.Topic;
import db.entity.User;
import org.junit.*;

import static org.junit.Assert.*;
public class TopicTest {
    @Test
    public void testTopicEntity() {
        Topic topic = new Topic();

        assertNotNull(topic);

        topic.setId(10);
        topic.setName("Медицина");

        assertEquals(topic.getId(), 10);
        assertEquals(topic.getName(), "Медицина");
        assertEquals(topic.toString(),"Category [name=Медицина,getId()=10]");


    }
}
