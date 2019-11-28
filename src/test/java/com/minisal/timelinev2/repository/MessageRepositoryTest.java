package com.minisal.timelinev2.repository;

import com.minisal.timelinev2.entity.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext
public class MessageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageRepository repo;

    private Message message1, message2, message3, message4, message5;

    @BeforeEach
    public void init() {

        message1 = new Message.MessageBuilder().title("title1").content("content1").user("user1").build();
        message2 = new Message.MessageBuilder().title("title1").content("content2").user("user2").build();
        message3 = new Message.MessageBuilder().title("title2").content("content1").user("user3").build();
        message4 = new Message.MessageBuilder().title("title3").content("content1").user("user1").build();
        message5 = new Message.MessageBuilder().title("title4").content("content1").user("user2").build();
    }

    @AfterEach
    public void clear(){
        this.entityManager.clear();
    }

    @Test
    public void testFindMessages() {
        this.entityManager.persist(message1);
        this.entityManager.persist(message2);
        this.entityManager.persist(message3);
        this.entityManager.persist(message4);
        this.entityManager.persist(message5);

        Iterable<Message> messages = repo.findAll();

        int count = 0;
        for (Message message : messages) {
            assertTrue(message.getTitle().contains("title"));
            assertTrue(message.getContent().contains("content"));
            assertTrue(message.getUser().contains("user"));
            count++;
        }
        assertEquals(5, count);
    }

    @Test
    public void testFindByTitle() {

        this.entityManager.persist(message1);
        this.entityManager.persist(message2);
        this.entityManager.persist(message3);
        this.entityManager.persist(message4);
        this.entityManager.persist(message5);

        List<Message> messages = repo.findByTitle("title1");
        int count = 0;
        for (Message message : messages) {
            assertTrue(message.getTitle().contains("title1"));
            System.out.println(message.toString());
            count++;
        }
        assertEquals(2, count);
    }
}
