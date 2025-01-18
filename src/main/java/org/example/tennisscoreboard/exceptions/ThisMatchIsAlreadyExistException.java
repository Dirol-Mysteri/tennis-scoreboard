package org.example.tennisscoreboard.exceptions;

public class ThisMatchIsAlreadyExistException extends RuntimeException {
    public ThisMatchIsAlreadyExistException() {
        super("This match is already exist");
    }
}
