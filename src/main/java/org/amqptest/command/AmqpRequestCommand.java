package org.amqptest.command;

import org.amqptest.ConnectionHandler;
import org.amqptest.exception.ProtocolException;

public interface AmqpRequestCommand {
    AmqpResponseCommand execute(ConnectionHandler connectionHandler) throws ProtocolException;

    void fillArguments(byte[] commandPayload);
}
