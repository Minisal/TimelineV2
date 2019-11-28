package com.minisal.timelinev2.service;

import com.minisal.timelinev2.entity.Message;
import com.minisal.timelinev2.exception.MessageNotFoundException;
import com.minisal.timelinev2.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> listMessages(){
        return (List<Message>)messageRepository.findAll();
    }

    @Override
    public Message findMessage(long id) throws MessageNotFoundException {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.get();
    }

    @Override
    public List<Message> getNow() {
        Calendar aDayAfter = Calendar.getInstance();
        Calendar aDayBefore = Calendar.getInstance();

        aDayAfter.add(Calendar.DATE, 1);
        aDayBefore.add(Calendar.DATE,-1);

        return messageRepository.findByAddTimeGreaterThanAndAddTimeLessThan(aDayBefore,aDayAfter);
    }

    @Override
    public List<Message> getNew(long id) {
        return messageRepository.findByIdGreaterThan(id);
    }

    @Override
    public List<Message> getOld(long id) {
        return messageRepository.findByIdLessThan(id);
    }
}
