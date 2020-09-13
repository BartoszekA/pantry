package io.dicedev.pantry.domain.exception;

public class PantryProductExpirationException extends RuntimeException {
    public PantryProductExpirationException(String message) {
        super(message);
    }
}
