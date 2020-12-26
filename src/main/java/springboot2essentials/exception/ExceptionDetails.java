package springboot2essentials.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class ExceptionDetails {

    public String title;
    public HttpStatus status;
    public String detais;
    public String developerMessage;
    public LocalDateTime timestamp;



    
}
