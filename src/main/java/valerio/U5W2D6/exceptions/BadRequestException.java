package valerio.U5W2D6.exceptions;


import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException{

    private List<ObjectError> errors;
    public BadRequestException(String message) {
        super(message);
}

public BadRequestException(List<ObjectError> errors) {
        super("Errore nella validazione dei dati");
        this.errors = errors;
}
}
