package it.iol.ws.validator;

public interface IValidator<T> {
    T validate() throws ValidationException;
}
