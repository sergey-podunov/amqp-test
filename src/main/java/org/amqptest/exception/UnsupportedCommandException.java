package org.amqptest.exception;

public class UnsupportedCommandException extends ProtocolException {

    public UnsupportedCommandException(int commandId, int methodId) {
        super(String.format("commandId: %s, methodId: %s", commandId, methodId));
    }
}
