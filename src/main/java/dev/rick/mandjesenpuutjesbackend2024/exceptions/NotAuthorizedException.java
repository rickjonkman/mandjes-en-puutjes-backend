package dev.rick.mandjesenpuutjesbackend2024.exceptions;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super("You are not authorized to perform this action");
    }
}
