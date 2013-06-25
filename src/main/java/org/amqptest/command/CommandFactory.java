package org.amqptest.command;

public interface CommandFactory<C extends AmqpCommand> {
    C createCommand(byte[] commandPayload);
}
