package com.crossvale.messageapp.service;

import com.crossvale.messageapp.dto.MessageDTO;
import com.crossvale.messageapp.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();
    Message getMessageById(Integer id);
    Message createMessage(MessageDTO message);
    Message updateMessage(Integer id, MessageDTO message);
    void deleteMessage(Integer id);
}
