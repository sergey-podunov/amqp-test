package org.amqptest.command.connection;

import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.types.LongString;

public class ConnectionSecure implements AmqpResponseCommand {

    private final LongString securityData;

    public ConnectionSecure(LongString securityData) {
        this.securityData = securityData;
    }

    @Override
    public short getCommandId() {
        return 10;
    }

    @Override
    public short getMethodId() {
        return 20;
    }

    @Override
    public byte[] bytes() {
        return securityData.bytes();
    }
}
