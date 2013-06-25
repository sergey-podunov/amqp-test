package org.amqptest.command.connection;

import org.amqptest.command.AmqpResponseCommand;

public class ConnectionCloseOk implements AmqpResponseCommand {
    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 51;
    }

    @Override
    public byte[] bytes() {
        return new byte[0];
    }

    @Override
    public String toString() {
        return "ConnectionCloseOk";
    }
}
