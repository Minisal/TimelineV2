package com.minisal.timelinev2.repository;

import com.minisal.timelinev2.entity.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext
public class MessageRepoDateTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private MessageRepository repo;

    private Message message1 = new Message.MessageBuilder().title("title1").content("content1").user("user1").build();
    private Message message2 = new Message.MessageBuilder().title("title1").content("content2").user("user2").build();
    private Message message3 = new Message.MessageBuilder().title("title2").content("content1").user("user3").build();
    private Message message4 = new Message.MessageBuilder().title("title3").content("content1").user("user1").build();
    private Message message5 = new Message.MessageBuilder().title("title4").content("content1").user("user2").build();

    @BeforeEach
    public void init() throws ParseException {
        message1.setAddTime("2019-11-10 10:10:10");
        message2.setAddTime("2019-11-10 10:10:10");
        message5.setAddTime("2020-11-10 10:10:10");
    }

    @AfterEach
    public void cleanup() {
        this.entityManager.clear();
    }

    @Test
    public void testFindByIdLessThan() {
        this.entityManager.persist(message1);
        this.entityManager.persist(message2);
        this.entityManager.persist(message3);
        this.entityManager.persist(message4);
        this.entityManager.persist(message5);

        Calendar aDayAfter = Calendar.getInstance();
        Calendar aDayBefore = Calendar.getInstance();

        aDayAfter.add(Calendar.DATE,1);
        aDayBefore.add(Calendar.DATE,-1);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aDayAfterStr = format.format(aDayAfter.getTime());
        String aDayBeforeStr = format.format(aDayBefore.getTime());

        List<Message> messages = repo.findByAddTimeGreaterThanAndAddTimeLessThan(aDayBefore,aDayAfter);;

        int count = 0;
        for (Message message : messages) {
            assertTrue(message.getAddTime().compareTo(aDayAfterStr)<0);
            assertTrue(message.getAddTime().compareTo(aDayBeforeStr)>0);
            System.out.println(message.getAddTime());
            count++;
        }
        assertEquals(2, count);
    }
}
