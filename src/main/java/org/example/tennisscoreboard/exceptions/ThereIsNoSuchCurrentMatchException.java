package org.example.tennisscoreboard.exceptions;

import java.util.UUID;

public class ThereIsNoSuchCurrentMatchException extends RuntimeException {
    public ThereIsNoSuchCurrentMatchException() {
        super("There is no such current match with the given match ID");
    }

    public ThereIsNoSuchCurrentMatchException(String message) {
        super(message);
    }

    // Добавление конструктора для передачи идентификатора матча
    public ThereIsNoSuchCurrentMatchException(UUID matchId) {
        super("There is no such current match with ID: " + matchId);
    }
}
