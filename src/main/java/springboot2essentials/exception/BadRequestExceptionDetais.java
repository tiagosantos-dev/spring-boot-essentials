package springboot2essentials.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadRequestExceptionDetais  extends ExceptionDetails{

    public BadRequestExceptionDetais(String title, HttpStatus badRequest, String detais, String developerMessage,
            LocalDateTime timestamp) {
        this.title = title;
        this.status = badRequest;
        this.detais = detais;
        this.developerMessage = developerMessage;
        this.timestamp = timestamp;
    }

    public BadRequestExceptionDetais() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDetais() {
        return detais;
    }

    public void setDetais(String detais) {
        this.detais = detais;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
