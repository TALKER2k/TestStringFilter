package ru.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.company.models.Message;
import ru.company.services.MessageService;
import ru.company.utils.exceptions.MessageCalculatedException;
import ru.company.utils.responses.MessageCalculatedResponse;

import javax.validation.Valid;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/frequency")
    public Map<Character, Integer> frequencyInString(@RequestBody @Valid Message message,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder msgErrors = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                msgErrors.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());
            }

            throw new MessageCalculatedException(msgErrors.toString());
        }

        return messageService.calculateSymbols(message);
    }

    @ExceptionHandler
    private ResponseEntity<MessageCalculatedResponse> exceptionHandler(MessageCalculatedException e) {
        MessageCalculatedResponse response = new MessageCalculatedResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
