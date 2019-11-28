package com.minisal.timelinev2.servise;

import com.minisal.timelinev2.entity.Message;
import com.minisal.timelinev2.exception.MessageNotFoundException;
import com.minisal.timelinev2.repository.MessageRepository;
import com.minisal.timelinev2.service.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageServiceImplTest {
    @Autowired
    private MessageServiceImpl service;

    @MockBean
    private MessageRepository messageRepository;

    private Message new_message;
    private List<Message> messages;
    private Calendar aDayBefore;
    private Calendar aDayAfter;
    private long id;

    @BeforeEach
    public void init(){
        new_message = new Message();
        new_message.setTitle("new title");
        new_message.setContent("new content");
        new_message.setUser("new user");
        id = 3;

        aDayAfter = Calendar.getInstance();
        aDayBefore = Calendar.getInstance();
        aDayAfter.add(Calendar.DATE, 1);
        aDayBefore.add(Calendar.DATE,-1);

        messages = new ArrayList<>();
        messages.add(new_message);
    }

    @Test
    public void should_list_messages() {

        when(messageRepository.findAll()).thenReturn(messages);
        List<Message> messageList = service.listMessages();
        verify(messageRepository,times(1)).findAll();
    }

    @Test
    public void should_find_messages_by_id() throws MessageNotFoundException {
        long id = 1;
        when(messageRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(new_message));
        Message message = service.findMessage(id);
        verify(messageRepository,times(1)).findById(id);
    }

    @Test
    public void should_get_message_add_now() {

        when(messageRepository.findByAddTimeGreaterThanAndAddTimeLessThan(any(Calendar.class),any(Calendar.class)))
                .thenReturn(messages);
        List<Message> messageList = service.getNow();
        verify(messageRepository,times(1)).findByAddTimeGreaterThanAndAddTimeLessThan(any(Calendar.class),any(Calendar.class));
    }

    @Test
    public void should_get_new_message() {

        when(messageRepository.findByIdGreaterThan(id))
                .thenReturn(messages);
        List<Message> messageList = service.getNew(id);
        verify(messageRepository,times(1)).findByIdGreaterThan(id);
    }

    @Test
    public void should_get_old_message() {

        when(messageRepository.findByIdLessThan(id))
                .thenReturn(messages);
        List<Message> messageList = service.getOld(id);
        verify(messageRepository,times(1)).findByIdLessThan(id);
    }
}
