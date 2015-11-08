package edu.sjsu.cmpe275.lab2.exception;

public class EntityNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityNotFound() {
    }

    public EntityNotFound(String arg0) {
        super(arg0);

    }
}
