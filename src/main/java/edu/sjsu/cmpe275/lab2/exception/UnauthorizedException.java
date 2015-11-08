package edu.sjsu.cmpe275.lab2.exception;


public class UnauthorizedException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
    }

    public UnauthorizedException(String arg0) {
        super(arg0);
    }

}
