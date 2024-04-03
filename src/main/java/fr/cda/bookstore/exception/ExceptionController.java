package fr.cda.bookstore.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.InstanceNotFoundException;
import java.time.LocalDateTime;

// @RestControllerAdvice -> Joue le rôle d'intercepteur
@RestControllerAdvice
public class ExceptionController {
    // @ExceptionHandler -> Définit les exceptions interceptées et traitées par la méthode qui vient juste en dessous
    @ExceptionHandler(InstanceNotFoundException.class)
    // ResponseEntity -> Classe qui wrap notre body pour ajouter des informations sur le code status et les headers
    public ResponseEntity<ExceptionMessage> notFound(HttpServletRequest request, InstanceNotFoundException exception){
        return new ResponseEntity<>(new ExceptionMessage(LocalDateTime.now(), request.getRequestURI(), "Instance not found"), HttpStatus.NOT_FOUND);
    }
}
