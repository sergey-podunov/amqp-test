package org.amqptest.command;

public interface AmqpResponseCommand {
    short getCommandId();

    short getMethodId();

    byte[] bytes();

    short getChannel();
}
