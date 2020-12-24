package springboot2essentials.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class ExceptionDetails {

    protected String title;
    protected HttpStatus status;
    protected String detais;
    protected String developerMessage;
    protected LocalDateTime timestamp;



    
}
