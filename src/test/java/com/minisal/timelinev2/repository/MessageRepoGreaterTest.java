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
public class MessageRepoGreaterTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageRepository repo;

    private Message message1, message2, message3, message4, message5;

    @BeforeEach
    public void initData() {
        message1 = new Message.MessageBuilder().title("title1").content("content1").user("user1").build();
        message2 = new Message.MessageBuilder().title("title1").content("content2").user("user2").build();
        message3 = new Message.MessageBuilder().title("title2").content("content1").user("user3").build();
        message4 = new Message.MessageBuilder().title("title3").content("content1").user("user1").build();
        message5 = new Message.MessageBuilder().title("title4").content("content1").user("user2").build();
    }

    @AfterEach
    public void cleanup() {
        this.entityManager.clear();
    }

    @Test
    public void testFindByIdGreaterThan() {
        this.entityManager.persist(message1);
        this.entityManager.persist(message2);
        this.entityManager.persist(message3);
        this.entityManager.persist(message4);
        this.entityManager.persist(message5);

        List<Message> messages = repo.findByIdGreaterThan(1L);

        int count = 0;
        for (Message message : messages) {
            assertTrue(message.getId()>1L);
            System.out.println(message.getId());
            count++;
        }
        assertEquals(4, count);
    }
}
