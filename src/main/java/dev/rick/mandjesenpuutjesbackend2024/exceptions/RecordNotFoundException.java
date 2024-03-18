package dev.rick.mandjesenpuutjesbackend2024.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(long id) {
        super("ID not found: "+id);
    }

    public RecordNotFoundException(String name) {
        super("Name not found: "+name);
    }

}
