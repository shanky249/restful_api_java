package com.crossvale.messageapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MessageDTO {
    @NotBlank
    private String message;
}
