package dev.rick.mandjesenpuutjesbackend2024.exceptions;

public class NameIsTakenException extends RuntimeException {

    public NameIsTakenException(String name) {
        super("Name is taken: "+name);
    }

    public NameIsTakenException(long id) {
        super("ID is taken: "+id);
    }
}
