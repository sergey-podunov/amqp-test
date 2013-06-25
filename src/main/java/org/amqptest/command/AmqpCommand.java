package org.amqptest.command;

import org.amqptest.ConnectionHandler;
import org.amqptest.exception.ProtocolException;

public interface AmqpCommand {
    short getCommandId();

    short getMethodId();

    AmqpCommand execute(ConnectionHandler connectionHandler) throws ProtocolException;

    byte[] bytes();

    void fillArguments(byte[] commandPayload);
}
