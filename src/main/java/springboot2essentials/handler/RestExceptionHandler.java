package springboot2essentials.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import springboot2essentials.exception.BadRequestException;
import springboot2essentials.exception.BadRequestExceptionDetais;
import springboot2essentials.exception.ValidationExceptionDetais;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetais> handlerBadRequestException(BadRequestException bre) {
        BadRequestExceptionDetais bred = new BadRequestExceptionDetais("Bad request Exception, check the Documentation",
                HttpStatus.BAD_REQUEST, bre.getMessage(), bre.getClass().getName(), LocalDateTime.now());
        return new ResponseEntity<BadRequestExceptionDetais>(bred, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetais> handlerArgumentNotValid(
            MethodArgumentNotValidException exception) {

        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();

        String fields = fieldErros.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String messageField = fieldErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationExceptionDetais ved = new ValidationExceptionDetais("Bad request Exception, check the Documentation",
                HttpStatus.BAD_REQUEST, "detais", "developerMessage", LocalDateTime.now(), fields, messageField);
        return ResponseEntity.badRequest().body(ved);
    }

}
