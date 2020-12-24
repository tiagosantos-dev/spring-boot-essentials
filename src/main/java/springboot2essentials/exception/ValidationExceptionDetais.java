package springboot2essentials.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ValidationExceptionDetais extends ExceptionDetails {
    
    protected String fields;
    protected String messages;

    public ValidationExceptionDetais(String fields, String messages) {
        this.fields = fields;
        this.messages = messages;
    }

    public ValidationExceptionDetais(String title, HttpStatus badRequest, String detais, String developerMessage,
    LocalDateTime timestamp, String fields, String messages ) {
    this.title = title;
    this.status = badRequest;
    this.detais = detais;
    this.developerMessage = developerMessage;
    this.timestamp = timestamp;
}
}
