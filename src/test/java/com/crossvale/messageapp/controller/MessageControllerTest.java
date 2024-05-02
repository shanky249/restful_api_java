package com.crossvale.messageapp.controller;

import com.crossvale.messageapp.dto.MessageDTO;
import com.crossvale.messageapp.entity.Message;
import com.crossvale.messageapp.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @Test
    void testGetAllMessages() {
        // Arrange
        List<Message> messages = Arrays.asList(
                new Message(1, "Hello"),
                new Message(2, "World")
        );
        when(messageService.getAllMessages()).thenReturn(messages);

        // Act
        ResponseEntity<List<Message>> response = messageController.getAllMessages();

        // Assert
        assertEquals(messages, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetMessageById() {
        // Arrange
        int id = 1;
        Message message = new Message(id, "Hello");
        when(messageService.getMessageById(id)).thenReturn(message);

        // Act
        ResponseEntity<Message> response = messageController.getMessageById(id);

        // Assert
        assertEquals(message, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCreateMessage() {
        // Arrange
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage("Hello"); // Set message here
        Message savedMessage = new Message(1, "Hello");
        when(messageService.createMessage(messageDTO)).thenReturn(savedMessage);

        // Act
        ResponseEntity<Message> response = messageController.createMessage(messageDTO);

        // Assert
        assertEquals(savedMessage, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testUpdateMessage() {
        // Arrange
        int id = 1;
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage("Hello"); // Set message here
        Message updatedMessage = new Message(id, "Hello");
        when(messageService.updateMessage(id, messageDTO)).thenReturn(updatedMessage);

        // Act
        ResponseEntity<Message> response = messageController.updateMessage(id, messageDTO);

        // Assert
        assertEquals(updatedMessage, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteMessage() {
        // Arrange
        int id = 1;
        doNothing().when(messageService).deleteMessage(id);

        // Act
        ResponseEntity<Void> response = messageController.deleteMessage(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(messageService, times(1)).deleteMessage(id);
    }
}
