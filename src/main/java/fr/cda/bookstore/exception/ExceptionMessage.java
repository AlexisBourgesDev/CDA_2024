package fr.cda.bookstore.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionMessage {
    private LocalDateTime date;
    private String request;
    private String message;
    private List<String> details;

    public ExceptionMessage(LocalDateTime date, String request, String message) {
        this.date = date;
        this.request = request;
        this.message = message;
    }

    public ExceptionMessage(LocalDateTime date, String request, String message, List<String> details) {
        this.date = date;
        this.request = request;
        this.message = message;
        this.details = details;
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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
