package org.amqptest.command;

public interface CommandFactory<C extends AmqpRequestCommand> {
    C createCommand(byte[] commandPayload);
}
