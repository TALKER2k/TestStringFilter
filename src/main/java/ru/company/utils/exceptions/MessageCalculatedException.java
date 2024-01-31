package ru.company.utils.exceptions;

public class MessageCalculatedException extends RuntimeException {
    public MessageCalculatedException(String str) {
        super(str);
    }
}
