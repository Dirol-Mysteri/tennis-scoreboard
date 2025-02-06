package org.example.tennisscoreboard.exceptions;

public class ThereIsNoSuchCurrentMatchException extends RuntimeException {
    public ThereIsNoSuchCurrentMatchException() {
        super("There is no such current match with the given match ID");
    }
}
