package com.support.it.exception;

public class NoSuchTicketException extends  Exception{
    private static final long serialVersionUID = 1L;

    public NoSuchTicketException() {
        super();
    }

    public NoSuchTicketException(String errors) {
        super(errors);

    }

}
