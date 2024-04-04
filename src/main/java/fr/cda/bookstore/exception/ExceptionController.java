package fr.cda.bookstore.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.InstanceNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

// @RestControllerAdvice -> Joue le rôle d'intercepteur
@RestControllerAdvice
// hérite de ResponseEntityExceptionHandler : méthode qui catch certains comportements d'erreurs par défautlors des appels comme le problème de paramètre manquant
public class ExceptionController extends ResponseEntityExceptionHandler {
    // @ExceptionHandler -> Définit les exceptions interceptées et traitées par la méthode qui vient juste en dessous
    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    // ResponseEntity -> Classe qui wrap notre body pour ajouter des informations sur le code status et les headers
    public ExceptionMessage notFound(HttpServletRequest request, InstanceNotFoundException exception) {
        return new ExceptionMessage(LocalDateTime.now(), request.getRequestURI(), "Instance not found");
    }

    @ExceptionHandler(IllegalStateException.class)
    public ExceptionMessage no(HttpServletRequest request, IllegalStateException exception) {
        return new ExceptionMessage(LocalDateTime.now(), request.getRequestURI(), "Instance not found");
    }

    // Appelé si paramètre GET manquant lors d'un appel à un point d'API (annoté @RequestParam sans required = false)
    // Par défaut, mis à required = true
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.badRequest().body(new ExceptionMessage(LocalDateTime.now(), request.getContextPath(), "Données manquantes", List.of(ex.getParameterName())));
    }

    // Appelé si une annotation de Spring Validations n'est pas respectée
    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> fieldsError = ex.getAllValidationResults().stream().map(ParameterValidationResult::getResolvableErrors).flatMap(List::stream).map(MessageSourceResolvable::getDefaultMessage).toList();
        return ResponseEntity.badRequest().body(new ExceptionMessage(LocalDateTime.now(), request.getContextPath(), "Données invalides", fieldsError));
    }
}
