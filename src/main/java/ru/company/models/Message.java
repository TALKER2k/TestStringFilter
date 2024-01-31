package ru.company.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Message {
    @Size(max = 25, message = "Size should be <25 symbols")
    @NotEmpty(message = "String should be not empty")
    String message;

    public Message() {}
}
