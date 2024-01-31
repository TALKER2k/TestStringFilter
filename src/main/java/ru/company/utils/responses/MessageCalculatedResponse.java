package ru.company.utils.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageCalculatedResponse {
    private String message;
    private Long timestamp;
}
