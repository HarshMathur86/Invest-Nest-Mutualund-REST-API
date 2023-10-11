package com.main.exception;

public class InvalidJsonRequestException extends Exception {

    public InvalidJsonRequestException() {
        super(); // Call the default constructor of the base Exception class.
    }

    public InvalidJsonRequestException(String message) {
        super(message); // Call the constructor of the base Exception class with a custom message.
    }

    public InvalidJsonRequestException(String message, Throwable cause) {
        super(message, cause); // Call the constructor of the base Exception class with a custom message and a cause.
    }

    public InvalidJsonRequestException(Throwable cause) {
        super(cause); // Call the constructor of the base Exception class with a cause.
    }
}

