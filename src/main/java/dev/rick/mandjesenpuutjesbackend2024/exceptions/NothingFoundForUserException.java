package dev.rick.mandjesenpuutjesbackend2024.exceptions;

public class NothingFoundForUserException extends RuntimeException {

    public NothingFoundForUserException(String username) {
        super("Nothing found for user: " + username);
    }
}
