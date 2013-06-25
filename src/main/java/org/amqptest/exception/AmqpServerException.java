package org.amqptest.exception;

public class AmqpServerException extends RuntimeException {
    public AmqpServerException(Exception e) {
        super(e);
    }

    public AmqpServerException(String s) {
        super(s);
    }
}
