package io.dicedev.pantry.domain.exception;

public class PantryProductNameLengthException extends RuntimeException {
    public PantryProductNameLengthException(String message) {
        super(message);
    }
}
