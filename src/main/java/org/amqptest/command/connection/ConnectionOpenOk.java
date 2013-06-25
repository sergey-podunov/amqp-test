package org.amqptest.command.connection;

import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.types.ShortString;

public class ConnectionOpenOk implements AmqpResponseCommand {
    private final String knownHosts;

    public ConnectionOpenOk(String knownHosts) {
        this.knownHosts = knownHosts;
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 41;
    }

    @Override
    public byte[] bytes() {
        return new ShortString(knownHosts).bytes();
    }
}
