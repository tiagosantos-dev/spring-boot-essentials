package springboot2essentials.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import springboot2essentials.exception.BadRequestException;
import springboot2essentials.exception.BadRequestExceptionDetais;
import springboot2essentials.exception.ExceptionDetails;
import springboot2essentials.exception.ValidationExceptionDetais;

@ControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler{

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetais> handlerBadRequestException(BadRequestException bre) {
        BadRequestExceptionDetais bred = new BadRequestExceptionDetais("Bad request Exception, check the Documentation",
                HttpStatus.BAD_REQUEST, bre.getMessage(), bre.getClass().getName(), LocalDateTime.now());
                bre.printStackTrace();
        return new ResponseEntity<BadRequestExceptionDetais>(bred, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {


        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();

        String fields = fieldErros.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String messageField = fieldErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationExceptionDetais ved = new ValidationExceptionDetais("Bad request Exception, invalid field",
        HttpStatus.BAD_REQUEST, "erro ao tentar submeter, pois existe algum campo vazio", MethodArgumentNotValidException.class.getName(), LocalDateTime.now(), fields, messageField);
        return ResponseEntity.badRequest().body(ved);
    
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

            ExceptionDetails bred = new BadRequestExceptionDetais(ex.getCause().getMessage(),
            status, ex.getMessage(), ex.getClass().getName(), LocalDateTime.now());
            ex.printStackTrace();

    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
        request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
    }
    return new ResponseEntity<>(bred, headers, status);
}


}
