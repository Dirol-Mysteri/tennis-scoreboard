package org.example.tennisscoreboard.exceptions;

import java.util.UUID;

public class ThisMatchIsAlreadyExistException extends RuntimeException {
    public ThisMatchIsAlreadyExistException() {
        super("This match is already exist");
    }

    public ThisMatchIsAlreadyExistException(String message) {
        super(message);
    }

    // Добавление конструктора для передачи идентификатора матча
    public ThisMatchIsAlreadyExistException(UUID matchId) {
        super("Match with ID " + matchId + " is already exists");
    }
}
