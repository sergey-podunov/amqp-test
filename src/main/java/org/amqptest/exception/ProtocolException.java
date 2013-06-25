package org.amqptest.exception;

public class ProtocolException extends Exception {
    public ProtocolException(Exception e) {
        super(e);
    }

    public ProtocolException() {
        super();
    }

    public ProtocolException(String message) {
        super(message);
    }
}
