package fr.cda.bookstore.exception;

import java.time.LocalDateTime;

public class ExceptionMessage {
    private LocalDateTime date;
    private String request;
    private String message;

    public ExceptionMessage(LocalDateTime date, String request, String message) {
        this.date = date;
        this.request = request;
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
