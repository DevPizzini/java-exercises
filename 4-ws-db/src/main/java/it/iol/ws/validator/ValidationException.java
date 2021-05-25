package it.iol.ws.validator;

import lombok.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ValidationException extends Exception {

    private List<String> errors = new ArrayList<>();

    public ValidationException() {
    }

    public ValidationException(@NonNull String error) {
        add(error);
    }

    public ValidationException(@NonNull List<String> errors) {
        this.errors = errors;
    }

    public void add(@NonNull String error) {
        errors.add(error);
    }

    public boolean isEmpty() {
        return errors.isEmpty();
    }
}

