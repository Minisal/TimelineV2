package com.minisal.timelinev2.service;

import com.minisal.timelinev2.entity.Message;
import com.minisal.timelinev2.exception.MessageNotFoundException;

import java.text.ParseException;
import java.util.List;

public interface MessageService {
    List<Message> listMessages();
    Message findMessage(long id) throws MessageNotFoundException;
    List<Message> getNew(long id) throws MessageNotFoundException;
    List<Message> getOld(long id) throws MessageNotFoundException;
    List<Message> getNow() throws MessageNotFoundException;
}
