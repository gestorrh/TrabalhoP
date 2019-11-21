package ufc.npi.proficiencia.exception;

import org.springframework.http.HttpStatus;

public class ProficienciaException
        extends Exception {
    private final String message;
    private final HttpStatus status;

    public ProficienciaException(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public ProficienciaException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}