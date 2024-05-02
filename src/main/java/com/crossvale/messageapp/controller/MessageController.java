package com.crossvale.messageapp.controller;

import com.crossvale.messageapp.dto.MessageDTO;
import com.crossvale.messageapp.entity.Message;
import com.crossvale.messageapp.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
@Api(tags = "Message Management")
public class MessageController {
    private final MessageService messageService;

    @ApiOperation("Get all messages")
    @ApiResponse(code = 200, message = "List of all messages retrieved successfully")
    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @ApiOperation("Get a message by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message retrieved successfully"),
            @ApiResponse(code = 404, message = "Message not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer id) {
        Message message = messageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }

    @ApiOperation("Create a new message")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Message created successfully"),
            @ApiResponse(code = 400, message = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<Message> createMessage(@Valid @RequestBody MessageDTO messageDTO) {
        Message message = messageService.createMessage(messageDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @ApiOperation("Update an existing message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message updated successfully"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 404, message = "Message not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Integer id, @Valid @RequestBody MessageDTO messageDTO) {
        Message updatedMessage = messageService.updateMessage(id, messageDTO);
        return ResponseEntity.ok(updatedMessage);
    }

    @ApiOperation("Delete a message by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Message deleted successfully"),
            @ApiResponse(code = 404, message = "Message not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
